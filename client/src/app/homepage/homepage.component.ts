import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

   readonly ROOT_URL = 'http://localhost:8080';
  templates : any;
  options : any;

  constructor(private http: HttpClient) {
  const header = new Headers();
   header.append('Access-Control-Allow-Origin', 'http://localhost:4200');
   this.options = new RequestOptions({header: header});
  }

  ngOnInit() {
  }
   getCatalog() {
      this.templates = this.http.get(this.ROOT_URL + '/greetingCard/catalog', {observe: 'response'})
      .subscribe(resp => {
                            this.templates = resp.body;
                            console.log(this.templates);
                            return this.templates;
                           });
    }

}
