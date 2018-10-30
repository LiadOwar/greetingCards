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
  selectedTemplate : any = 'na';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }
  getRootURL(){
    return this.ROOT_URL;
  }
  getSelectedTemplateType(){
    return this.selectedTemplate.type;
  }

  getCatalog() {
    const url = this.ROOT_URL + '/greetingCard/catalog';
    this.http.get(url).subscribe((response) => this.templates = <any[]>response);
  }

  selectTemplate(template: any){
   console.log("selectTemplate" + template.type);
   this.selectedTemplate = template;
  }

  unSelectTemplate(){
    this.selectedTemplate = 'na';
  }

}
