import {Component, OnInit, ViewChild, ViewContainerRef, ComponentFactoryResolver} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GetwellCardComponent} from "../greetingCard/getwell-card/getwell-card.component";
import {BirthdayCardComponent} from "../greetingCard/birthday-card/birthday-card.component";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  readonly ROOT_URL = 'http://localhost:8080';
  templates: any[] = [];
  selectedTemplate: any = 'na';
  savedGreetingCards: any[] = [];
  showGetYourCardsList: any = false;
  showHideText: any = 'See Your Cards';
  componentRef: any;
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
    console.log("selectTemplate" + template.type);
    this.selectedTemplate = template;
  }

  unSelectTemplate() {
    this.selectedTemplate = 'na';
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

      console.log("internal length " + this.savedGreetingCards.length);
      for (let i = 0; i < this.savedGreetingCards.length; i++) {
        console.log("i =  " + i + " savedGreetingCards[i].templateType " + this.savedGreetingCards[i].templateType);

        if (this.savedGreetingCards[i].templateType == 'BIRTH_DAY_TEMPLATE') {
          component = BirthdayCardComponent;
        } else if (this.savedGreetingCards[i].templateType == 'GET_WELL_SOON_TEMPLATE') {
          component = GetwellCardComponent;
        }
        const factory = this.resolver.resolveComponentFactory(component);
        this.componentRef = this.entry.createComponent(factory);
        console.log(this.savedGreetingCards[i]);
        (<any>this.componentRef.instance).cardData = this.savedGreetingCards[i];
      }
      this.showHideText = 'Hide Your Cards';
      this.showGetYourCardsList = true;
    }
    else if (this.showGetYourCardsList){
      this.entry.clear();
      this.showHideText = 'See Your Cards';
      this.showGetYourCardsList = false;
    }
    console.log("updated showGetYourCardsList " + this.showGetYourCardsList)
  }

}
