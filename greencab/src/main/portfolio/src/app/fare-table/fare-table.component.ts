import {Component, Input, OnInit} from '@angular/core';
import {Fare} from "../../model/Fare";

@Component({
  selector: 'app-fare-table',
  templateUrl: './fare-table.component.html',
  styleUrls: ['./fare-table.component.scss']
})
export class FareTableComponent implements OnInit {

  @Input()
  fares: Array<Fare> | undefined

  constructor() { }

  ngOnInit(): void {
  }

}
