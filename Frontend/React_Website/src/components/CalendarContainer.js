import { Calendar, dateFnsLocalizer } from 'react-big-calendar';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import startOfWeek from 'date-fns/startOfWeek';
import getDay from 'date-fns/getDate';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import React, { useState, useEffect } from 'react';
import './CalendarContainer.css'; 
import axios from "axios";

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

function CalendarContainer() {

const [calendarCourses, setCalendarCourse] = useState([]);

const loadCalendarCourse=async()=> {
  const result = await axios.get("http://localhost:8080/api/courses/schedule/all/iso8601?weeks=2");
  setCalendarCourse(result.data);
}

useEffect(() => {
  loadCalendarCourse();
  },[]);

  const events = calendarCourses.map((calendarCourse)=> {
    return {
      title: calendarCourse.course_name,
      start: new Date(calendarCourse.start),
      end : new Date(calendarCourse.end),
      desc : calendarCourse.room
    }
  })

  return (
    <>
      <div className="calendarContainer">
      <Calendar localizer={localizer} events={events}
      starAccessor="start" endAccessor="end" style={{height: 400, margin: "20px"}} />
      </div>
    </>
  );
}

export default CalendarContainer;