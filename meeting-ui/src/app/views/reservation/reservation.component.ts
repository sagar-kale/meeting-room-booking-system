import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  NgbCalendar,
  NgbDateAdapter,
  NgbDatepickerConfig,
  NgbDateStruct,
  NgbTimepickerConfig,
  NgbTimeStruct
} from '@ng-bootstrap/ng-bootstrap';
import { ModalDirective } from 'angular-bootstrap-md';
import { ToastService } from '../toast/toast.service';
import { Meeting } from './meeting';
import { MeetingService } from './meeting.service';
import { Reservation } from './reserv.model';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {



  constructor(config: NgbTimepickerConfig,
    dateConfig: NgbDatepickerConfig,
    private formBuilder: FormBuilder,
    private ngbCalendar: NgbCalendar,
    private dateAdapter: NgbDateAdapter<NgbDateStruct>,
    private service: MeetingService,
    public toastService: ToastService) {
    // customize default values of ratings used by this component tree
    config.seconds = false;
    config.spinners = false;
    dateConfig.minDate = this.today;
  }

  get today() {
    return this.dateAdapter.toModel(this.ngbCalendar.getToday());
  }

  get f() {
    return this.meetingGroup.controls;
  }

  get r() {
    return this.reservationForm.controls;
  }

  @ViewChild('centralLarge') public showModalOnClick: ModalDirective;

  meetingGroup: FormGroup;
  reservationForm: FormGroup;
  timeFrom: NgbTimeStruct = { hour: 0, minute: 0, second: 0 };
  timeTo: NgbTimeStruct = { hour: 0, minute: 0, second: 0 };
  dateFrom: string;
  dateTo: string;
  meetingRooms: Meeting[];
  reservation: Reservation;
  currentBookingRoomName: string;
  readonly DEFAULT_DATE = '00-00-0000 00:00:00';
  checkBtnText = 'Check Availability';
  bookBtnText = 'Book';

  ngOnInit(): void {
    this.buildMeetingForm();
    this.buildReservationForm();
  }


  onSubmit(): void {
    this.checkBtnText = 'Checking ...';
    const datetimeFrom = `${this.service.formatDate(this.f.dateFrom.value)} ${this.service.formateTime(this.f.timeFrom.value)}`;
    const dateTimeTo = `${this.service.formatDate(this.f.dateTo.value)} ${this.service.formateTime(this.f.timeTo.value)}`;
    this.service.checkRoomAvailability(datetimeFrom, dateTimeTo).subscribe(value => {
      this.meetingRooms = value;
      // console.log(value);
      this.checkBtnText = 'Check Availability';
    });
  }

  loadMeetingToBook(roomId: string) {
    this.service.getMeetingById(roomId).subscribe((value: Meeting) => {
      this.currentBookingRoomName = value.name;
      this.r.roomId.setValue(value.id);
      this.r.dateFrom.setValue(this.f.dateFrom.value);
      this.r.dateTo.setValue(this.f.dateTo.value);
      this.r.timeFrom.setValue(this.f.timeFrom.value);
      this.r.timeTo.setValue(this.f.timeTo.value);
    });
  }

  bookMeetingRoom(): void {
    this.bookBtnText = 'Booking';
    const datetimeFrom = `${this.service.formatDate(this.r.dateFrom.value)} ${this.service.formateTime(this.r.timeFrom.value)}`;
    const datetimeTo = `${this.service.formatDate(this.r.dateTo.value)} ${this.service.formateTime(this.r.timeTo.value)}`;
    this.r.dateBegin.setValue(datetimeFrom);
    this.r.dateEnd.setValue(datetimeTo);
    // console.log(this.reservationForm.value);
    this.service.reservRoom(this.reservationForm.value).subscribe(res => {
      if (res.status === 201) {
        this.showModalOnClick.hide();
        this.service.showSuccess(res.statusText);
      } else {
        this.toastService.show('some error occurred , please check the date and time');
      }
      // console.log('reservation status ::', res);
      this.clearForm();
    });
  }

  public showModal(id: string): void {
    this.loadMeetingToBook(id);
    this.showModalOnClick.show();

  }

  buildMeetingForm(): void {
    this.meetingGroup = this.formBuilder.group({
      dateFrom: ['', Validators.required],
      dateTo: ['', Validators.required],
      timeFrom: ['', Validators.required],
      timeTo: ['', Validators.required]
    });
  }

  buildReservationForm(): void {
    this.reservationForm = this.formBuilder.group({
      roomId: ['', Validators.required],
      purpose: ['', Validators.required],
      dateFrom: ['', Validators.required],
      dateTo: ['', Validators.required],
      dateBegin: [this.DEFAULT_DATE, Validators.required],
      dateEnd: [this.DEFAULT_DATE, Validators.required],
      timeFrom: ['', Validators.required],
      timeTo: ['', Validators.required],
      employeeId: ['', Validators.required],
    });
  }
  clearForm() {
    this.currentBookingRoomName = '';
    this.reservationForm.reset();
    this.meetingGroup.reset();
    this.meetingRooms = [];
    this.bookBtnText = 'Book';
  }

}
