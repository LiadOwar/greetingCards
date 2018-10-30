import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-getwell-card',
  templateUrl: './getwell-card.component.html',
  styleUrls: ['./getwell-card.component.css']
})
export class GetwellCardComponent implements OnInit {
  @Input() cardData : any;
  constructor() {
  }

  ngOnInit() {
    
  }

}
