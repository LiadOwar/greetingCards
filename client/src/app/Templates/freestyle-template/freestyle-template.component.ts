import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { HttpHeaders } from '@angular/common/http';
import {HomepageComponent} from "../../homepage/homepage.component";

@Component({
  selector: 'app-freestyle-template',
  templateUrl: './freestyle-template.component.html',
  styleUrls: ['./freestyle-template.component.css']
})
export class FreestyleTemplateComponent implements OnInit {

    ROOT_URL: any;
    httpOptions: any;
    templateType: any;
    freeText: any;
    homePage : HomepageComponent;
    greetingCard: NoTemplateGreetingCard;



  constructor(private http: HttpClient, homePage : HomepageComponent) {
      this.homePage = homePage;
          this.ROOT_URL = homePage.getRootURL();
          this.templateType = 'NA';
          this.httpOptions =  {headers: new HttpHeaders({
            'Content-Type':  'application/json',
            'Authorization': 'my-auth-token'
          })};

    }

  ngOnInit() {
  }
    createCard(){
      const url = this.ROOT_URL + '/greetingCard/createCard';
      this.greetingCard = new NoTemplateGreetingCard(this.templateType, this.freeText);
      console.log("posting " + this.greetingCard + "to " + url);
      this.http.post(url, this.greetingCard, this.httpOptions).subscribe((response) => console.log("Response: " + response));
    }

    cancel(){
      this.homePage.unSelectTemplate();
    }

}

class NoTemplateGreetingCard {
  templateType: any;
  freeText : any;
  constructor(templateType, freeText) {
    this.templateType = templateType;
    this.freeText = freeText;
  }
  }
