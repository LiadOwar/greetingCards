import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { BirthdayTemplateComponent } from './Templates/birthday-template/birthday-template.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    BirthdayTemplateComponent
  ],
  imports: [
    BrowserModule, HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
