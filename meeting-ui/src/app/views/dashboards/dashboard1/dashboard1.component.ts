import { Component, OnInit } from '@angular/core';
import { MeetingService } from '../../reservation/meeting.service';

@Component({
  selector: 'app-dashboard1',
  templateUrl: './dashboard1.component.html',
  styleUrls: ['./dashboard1.component.scss']
})
export class Dashboard1Component implements OnInit {

  public map: any = { lat: 51.678418, lng: 7.809007 };
  public chart1Type = 'bar';
  public chart2Type = 'pie';
  public chart3Type = 'line';
  public chart4Type = 'radar';
  public chart5Type = 'doughnut';

  public chartType = 'line';

  reservations: Array<any> = [];

  public chartDatasets: Array<any> = [
    { data: [50, 40, 60, 51, 56, 55, 40], label: '#1' },
    { data: [28, 80, 40, 69, 36, 37, 110], label: '#2' },
    { data: [38, 58, 30, 90, 45, 65, 30], label: '#3' }
  ];

  public chartLabels: Array<any> = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'];

  public chartColors: Array<any> = [

  ];

  public dateOptionsSelect: any[];
  public bulkOptionsSelect: any[];
  public showOnlyOptionsSelect: any[];
  public filterOptionsSelect: any[];

  public chartOptions: any = {
    responsive: true,
    legend: {
      labels: {
        fontColor: '#5b5f62',
      }
    },
    scales: {
      yAxes: [{
        ticks: {
          fontColor: '#5b5f62',
        }
      }],
      xAxes: [{
        ticks: {
          fontColor: '#5b5f62',
        }
      }]
    }
  };


  constructor(private service: MeetingService) { }


  ngOnInit(): void {
    this.service.getAllFutureReservations().subscribe({
      next: res => {
        this.reservations = res;
      }
    });
  }


}
