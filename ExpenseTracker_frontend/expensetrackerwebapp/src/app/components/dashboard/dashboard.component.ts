import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { BarController, BarElement, CategoryScale, Chart, Legend, LinearScale, LineController, LineElement, PointElement, Title, Tooltip } from 'chart.js';
import { StatsService } from 'src/app/services/stats/stats.service';

Chart.register(CategoryScale, LinearScale, BarController, BarElement, Title, Tooltip, Legend, LineController, PointElement, LineElement);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements AfterViewInit {

  stats: any;
  incomes: any;
  expenses: any;

  gridStyle = {
    width: '25%',
    textAlign: 'center'
  };

  @ViewChild('incomeLineChartRef') private incomeLineChartRef: any;
  @ViewChild('expenseLineChartRef') private expenseLineChartRef: any;

  constructor(private statsService: StatsService) {}

  ngAfterViewInit() {
    this.getStats();
    this.getChartDate();
  }

  createLineChart() {
    const incomectx = this.incomeLineChartRef.nativeElement.getContext('2d');

    new Chart(incomectx, {
      type: 'line',
      data: {
        labels: this.incomes.map(income=>income.date),
        datasets: [
          {
            label:'Income' ,
            data:this.incomes.map(income=>income.amount),
            borderWidth: 1,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
            ],
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    const expensectx = this.expenseLineChartRef.nativeElement.getContext('2d');

    new Chart(expensectx, {
      type: 'line',
      data: {
        labels: this.expenses.map(income=>income.date),
        datasets: [
          {
            label: 'Expense',
            data: this.expenses.map(income=>income.amount),
            borderWidth: 1,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
            ],
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  getStats() {
    this.statsService.getStats().subscribe(
      res => {
        console.log(res);
        this.stats = res;
      },
      error => {
        console.error(error);
      }
    );
  }

  getChartDate() {
    this.statsService.getChart().subscribe(
      res => {
        console.log('Incomes:', this.incomes);
        console.log('Expenses:', this.expenses);
        if (res.expenseList && res.incomeList) {
          this.incomes = res.incomeList;
          this.expenses = res.expenseList;
          console.log(res);

          // Call chart initialization here
          this.createLineChart();
        } else {
          console.error('Chart data missing:', res);
        }
      },
      error => {
        console.error(error);
      }
    );
  }
}
