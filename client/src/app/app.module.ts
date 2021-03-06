import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { BirthdayTemplateComponent } from './Templates/birthday-template/birthday-template.component';
import { GetwellTemplateComponent } from './Templates/getwell-template/getwell-template.component';
import { BirthdayCardComponent } from './greetingCard/birthday-card/birthday-card.component';
import { GetwellCardComponent } from './greetingCard/getwell-card/getwell-card.component';
import { FreestyleTemplateComponent } from './Templates/freestyle-template/freestyle-template.component';
import { FreestyleCardComponent } from './greetingCard/freestyle-card/freestyle-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    BirthdayTemplateComponent,
    GetwellTemplateComponent,
    BirthdayCardComponent,
    GetwellCardComponent,
    FreestyleTemplateComponent,
    FreestyleCardComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [BirthdayCardComponent, GetwellCardComponent, FreestyleCardComponent]
})
export class AppModule { }
