import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  readonly ROOT_URL = 'http://localhost:8080';
  templates: any[] = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  getCatalog() {
    const url = this.ROOT_URL + '/greetingCard/catalog';
    this.http.get(url).subscribe((response) => this.templates = <any[]>response);
  }

}
