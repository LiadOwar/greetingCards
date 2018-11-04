import { Component, OnInit, Input} from '@angular/core';
//import {GetwellTemplateComponent} from "../../Templates/getwell-template/getwell-template.component";

@Component({
  selector: 'app-getwell-card',
  templateUrl: './getwell-card.component.html',
  styleUrls: ['./getwell-card.component.css']
})
export class GetwellCardComponent implements OnInit {
  @Input() cardData : any;
  @Input() templateText : any[];

  constructor() {
  }

  ngOnInit() {

  }

}
