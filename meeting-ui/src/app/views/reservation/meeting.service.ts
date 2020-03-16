import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Meeting } from './meeting';
import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { ToastService } from '../toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class MeetingService {

  private global_url = '/api';
  private reserv_url = '/reservation';
  readonly DELIMITER = '-';
  httpOptions = {
    headers: new HttpHeaders({ 'X-MeetingUi-Auth-Token': 'mxjsweiiaofhueradknron' })
  };


  constructor(
    private http: HttpClient,
    public toastService: ToastService) { }

  formatDate(date: NgbDateStruct): string {
    let result = '';
    if (date) {
      result = date.year + this.DELIMITER + date.month + this.DELIMITER + date.day;
    }
    return result;
  }

  formateTime(time: NgbTimeStruct): string {
    if (!time) {
      return '';
    }
    return `${this.pad(time.hour)}:${this.pad(time.minute)}:${this.pad(time.second)}`;
  }

  private pad(i: number): string {
    return i < 10 ? `0${i}` : `${i}`;
  }


  showSuccess(msg: string) {
    this.toastService.show(msg, { classname: 'bg-success text-light', delay: 10000 });
  }

  getMeetings() {
    return this.http.get('https://reqres.in/api/Meetings');
  }

  getAllMeetings(): Observable<Meeting[]> {
    return this.http.get<Meeting[]>(this.global_url + '/all', this.httpOptions); /*.pipe(map((response: any) => {
        console.log(response)
        return response.Meetings;
      }));*/
  }

  getAllBookings(): Observable<Array<any>> {
    return this.http.get<Array<any>>(this.global_url + this.reserv_url + '/get', this.httpOptions); /*.pipe(map((response: any) => {
        console.log(response)
        return response.Meetings;
      }));*/
  }

  getAllFutureReservations(): Observable<Array<any>> {
    return this.http.get<Array<any>>(this.global_url + this.reserv_url, this.httpOptions); /*.pipe(map((response: any) => {
        console.log(response)
        return response.Meetings;
      }));*/
  }

  addMeeting(meeting: Meeting) {
    // console.log('under service ', meeting);
    return this.http.post(this.global_url + '/add', meeting, this.httpOptions);
  }

  reservRoom(meeting: Meeting): Observable<Response> {
    // console.log('under booking room ', meeting);
    return this.http.post<Response>(this.global_url + this.reserv_url + '/book', meeting, this.httpOptions);
  }

  checkRoomAvailability(dateFrom: string, dateTo: string): Observable<Meeting[]> {
    const body = { df: dateFrom, dt: dateTo };
    return this.http.post<Meeting[]>(this.global_url + '/meetingroom/date', body, this.httpOptions);
  }

  getMeetingById(meetingId: string): Observable<Meeting> {
    return this.http.get<Meeting>(`${this.global_url}/meetingroom/${meetingId}`, this.httpOptions);
  }

  updateMeeting(meeting: Meeting): Observable<Meeting> {

    return this.http.put<Meeting>(this.global_url + '/update/' + meeting.id, meeting, this.httpOptions);
  }

  deleteMeetingByMeetingId(meetingId: string): Observable<string> {
    return this.http.delete<string>(this.global_url + '/delete/' + meetingId, this.httpOptions);
  }
}
