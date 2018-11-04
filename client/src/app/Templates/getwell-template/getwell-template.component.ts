import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HomepageComponent} from "../../homepage/homepage.component";
import { HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Component({
  selector: 'app-getwell-template',
  templateUrl: './getwell-template.component.html',
  styleUrls: ['./getwell-template.component.css']
})
export class GetwellTemplateComponent implements OnInit {
  ROOT_URL: any;
  httpOptions: any;
  senderName: any;
  recipientName: any;
  templateType: any;
  greetingCard: GetWellGreetingCard;
  homePage : HomepageComponent;

  templateText : any[];

  allTextLines= [];//  {textLine1, textLine2, textLine3};

  constructor(private http: HttpClient, homePage : HomepageComponent) {
    this.homePage = homePage;
    this.templateText = this.homePage.getWellTemplateText;




    this.ROOT_URL = homePage.getRootURL();
    this.templateType = homePage.getSelectedTemplateType();
    this.httpOptions =  {headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })};

  }

  ngOnInit() {
  console.log("in get well template --- init");

  }

  createCard(){
    const url = this.ROOT_URL + '/greetingCard/createCard';
    this.greetingCard = new GetWellGreetingCard(this.templateType, this.senderName, this. recipientName);
    console.log("posting " + this.greetingCard + "to " + url);
    this.http.post(url, this.greetingCard, httpOptions).subscribe((response) => console.log("Response: " + response));
  }

  cancel(){
    this.homePage.unSelectTemplate();
  }

}
class GetWellGreetingCard {
  senderName: any;
  recipientName: any;
  templateType: any;

  constructor(templateType, senderName, recipientName) {
    this.templateType = templateType;
    this.senderName = senderName;
    this.recipientName = recipientName;
  }
}
