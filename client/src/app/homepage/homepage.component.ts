import {Component, OnInit, ViewChild, ViewContainerRef, ComponentFactoryResolver} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GetwellCardComponent} from "../greetingCard/getwell-card/getwell-card.component";
import {BirthdayCardComponent} from "../greetingCard/birthday-card/birthday-card.component";
import {FreestyleCardComponent} from "../greetingCard/freestyle-card/freestyle-card.component";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  readonly ROOT_URL = 'http://localhost:8080';
  templates: any[] = [];
  selectedTemplate: any;
  savedGreetingCards: any[] = [];
  showGetYourCardsList: any = false;
  showHideText: any = 'See Your Cards';
  componentRef: any;

  getWellTemplateText: any[] = ['Dear ','Get well soon.', 'From'];
  birthDayTemplateText: any[] = ['Happy' ,'Birthday', 'Wishes you all the best in the world', 'From'];


  @ViewChild('newcardcontainer', {read: ViewContainerRef}) entry: ViewContainerRef;

  constructor(private http: HttpClient, private resolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
  }

  getRootURL() {
    return this.ROOT_URL;
  }

  getSelectedTemplateType() {
    return this.selectedTemplate.type;
  }

  getCatalog() {
    const url = this.ROOT_URL + '/greetingCard/catalog';
    this.http.get(url).subscribe((response) => this.templates = <any[]>response);
  }

  selectTemplate(template: any) {
    console.log("selectTemplate " + template.type);
    this.selectedTemplate = template;
  }

    selectNoTemplate() {
      console.log("select NoTemplate");
      this.selectedTemplate = 'NA';
    }

  unSelectTemplate() {
    this.selectedTemplate = null;
  }

  getSavedGreetingCards() {
    const url = this.ROOT_URL + '/greetingCard/getAllCards';
    this.http.get(url).subscribe((response) => {
      this.savedGreetingCards = <any[]>response;
      console.log("length " + this.savedGreetingCards.length);
      this.createSavedCardsComponent();
    });
    console.log(this.savedGreetingCards);
  }

  createSavedCardsComponent() {
    if (!this.showGetYourCardsList) {

      this.entry.clear();
      let component;
      let templateText;

      console.log("internal length " + this.savedGreetingCards.length);
      for (let i = 0; i < this.savedGreetingCards.length; i++) {
        //console.log("i =  " + i + " savedGreetingCards[i].templateType " + this.savedGreetingCards[i].templateType);

        if (this.savedGreetingCards[i].templateType == 'BIRTH_DAY_TEMPLATE') {
          component = BirthdayCardComponent;
          templateText = this.birthDayTemplateText;
        } else if (this.savedGreetingCards[i].templateType == 'GET_WELL_SOON_TEMPLATE') {
          component = GetwellCardComponent;
          templateText = this.getWellTemplateText;
        }
         else if (this.savedGreetingCards[i].templateType == 'NA') {
                  component = FreestyleCardComponent;

                }
        const factory = this.resolver.resolveComponentFactory(component);
        this.componentRef = this.entry.createComponent(factory);
        //console.log(this.savedGreetingCards[i]);
        (<any>this.componentRef.instance).cardData = this.savedGreetingCards[i];
        (<any>this.componentRef.instance).templateText = templateText;

      }
      this.showHideText = 'Hide Your Cards';
      this.showGetYourCardsList = true;
    }
    else if (this.showGetYourCardsList){
      this.entry.clear();
      this.showHideText = 'See Your Cards';
      this.showGetYourCardsList = false;
    }
    //console.log("updated showGetYourCardsList " + this.showGetYourCardsList)
  }

  createPlainTemplateCard(){
    const url = this.ROOT_URL + '/greetingCard/catalog';
        this.http.get(url).subscribe((response) => this.templates = <any[]>response);
}
}
