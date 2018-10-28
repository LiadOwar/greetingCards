import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { BirthdayTemplateComponent } from './Templates/birthday-template/birthday-template.component';
import { GetwellTemplateComponent } from './Templates/getwell-template/getwell-template.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    BirthdayTemplateComponent,
    GetwellTemplateComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
