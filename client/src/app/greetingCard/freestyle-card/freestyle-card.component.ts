import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-freestyle-card',
  templateUrl: './freestyle-card.component.html',
  styleUrls: ['./freestyle-card.component.css']
})
export class FreestyleCardComponent implements OnInit {
  freeText : any;

  @Input() cardData : any;
  constructor() {
   }

  ngOnInit() {
  this.freeText = this.cardData.freeText;
  if (this.freeText != null){
    this.freeText = this.freeText.replace(/\n/g, "<br />");
  }
  //
  }

}
