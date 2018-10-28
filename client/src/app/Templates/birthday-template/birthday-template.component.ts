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
  selector: 'app-birthday-template',
  templateUrl: './birthday-template.component.html',
  styleUrls: ['./birthday-template.component.css']
})
export class BirthdayTemplateComponent implements OnInit {

  ROOT_URL: any;
  httpOptions: any;
  senderName: any;
  recipientAge: any;
  recipientName: any;
  templateType: any;
  greetingCard: BirthdayGreetingCard;

  constructor(private http: HttpClient, homePage : HomepageComponent) {
    this.ROOT_URL = homePage.getRootURL();
    this.templateType = homePage.getSelectedTemplateType();
    this.httpOptions =  {headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })};
  }

  ngOnInit() {

  }

  createCard(){
    const url = this.ROOT_URL + '/greetingCard/createCard';
    this.greetingCard = new BirthdayGreetingCard(this.templateType, this.senderName, this.recipientAge, this. recipientName);
    console.log("posting " + this.greetingCard + "to " + url);
    this.http.post(url, this.greetingCard, httpOptions).subscribe((response) => console.log("Response: " + response));
  }

}
class BirthdayGreetingCard {
  senderName: any;
  recipientAge: any;
  recipientName: any;
  templateType: any;
  constructor(templateType, senderName, recipientAge, recipientName ) {
    this.templateType = templateType;
    this.senderName = senderName;
    this.recipientName = recipientName;
    this.recipientAge = recipientAge;
  }
}
