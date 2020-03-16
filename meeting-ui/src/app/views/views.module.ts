import { AgmCoreModule } from '@agm/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarModule } from 'angular-calendar';
import { FooterComponent } from '../main-layout/footer/footer.component';
import { SharedModule } from '../shared/shared.module';
import { StatsCardComponent } from './dashboards/common/stats-card/stats-card.component';
import { StatsCard2Component } from './dashboards/common/stats-card2/stats-card2.component';
import { Dashboard1Component } from './dashboards/dashboard1/dashboard1.component';
import { HelpComponent } from './help/help.component';
import { Map1Component } from './maps/map1/map1.component';
import { ModalsComponent } from './modals/modals.component';
import { Profile1Component } from './profile/profile1/profile1.component';
import { BasicTableComponent } from './tables/basic-table/basic-table.component';
import { ToastsContainer } from './toast/toasts-container.component';
import { ReservationComponent } from './reservation/reservation.component';
import { CustomDateFormatterPipe } from './custom-date-formatter.pipe';




@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    SharedModule,
    NgbModule,
    AgmCoreModule.forRoot({
      // https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en#key
      apiKey: ''
    }),
    CalendarModule.forRoot()
  ],
  declarations: [
    FooterComponent,
    BasicTableComponent,
    ModalsComponent,
    Map1Component,
    StatsCardComponent,
    StatsCard2Component,
    Dashboard1Component,
    Profile1Component,
    HelpComponent,
    ToastsContainer,
    ReservationComponent,
    CustomDateFormatterPipe

  ],
  exports: [
    FooterComponent,
    BasicTableComponent,
    ModalsComponent,
    Map1Component,
    StatsCardComponent,
    StatsCard2Component,
    Dashboard1Component,
    ToastsContainer,
    CustomDateFormatterPipe
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class ViewsModule { }
