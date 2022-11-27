import { Calendar, dateFnsLocalizer } from 'react-big-calendar';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import startOfWeek from 'date-fns/startOfWeek';
import getDay from 'date-fns/getDate';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import React, { useState } from 'react';
import './CalendarContainer.css'; 

const locales = {
  "en-US" : require("date-fns/locale/en-US")
}
const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

//api events
const events = [
  {
    title: "Pilates",
    start: new Date(2022, 10+1,26,8),
    end : new Date(2022, 10+1,26,9)
  },
  {
    title: "TRX",
    start: new Date(2022, 10,27,5),
    end : new Date(2022, 10,27,7)
  }
]

function CalendarContainer() {

  return (
    <div className="calendarContainer">
    <Calendar localizer={localizer} events={events}
    starAccessor="start" endAccessor="end" style={{height: 400, margin: "20px"}} />
    </div>
  );
}

export default CalendarContainer;