import { v4 as uuidv4 } from "uuid";

export class Alarm {
  constructor(sensor, value, lastUpDate, condition) {
    this.id = uuidv4();
    this.sensor = sensor.name;
    this.type = "danger";
    this.msg = `Allarme!!!, il valore segnalato (${value}) supera il valore di soglia impostato(${condition}${sensor.alertValue})`;
    this.time = lastUpDate;
  }
}
export class Warning {
  constructor(sensor, value, lastUpDate) {
    this.id = uuidv4();
    this.sensor = sensor.name;
    this.type = "warning";
    this.msg = `Malfunzionamento sensore, il valore segnalato (${value}) è al di fuori dei parametri di funzionamento(max: ${sensor.rangeMax}, min:${sensor.rangeMin})`;
    this.time = lastUpDate;
  }
}
