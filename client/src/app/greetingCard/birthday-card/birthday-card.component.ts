import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-birthday-card',
  templateUrl: './birthday-card.component.html',
  styleUrls: ['./birthday-card.component.css']
})
export class BirthdayCardComponent implements OnInit {

  @Input() cardData : any;
  show : any = false;
  constructor() {
  }

  ngOnInit() {
  
  }

}
