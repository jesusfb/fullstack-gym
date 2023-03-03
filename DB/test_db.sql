use project;

INSERT INTO PLANS (PLAN_ID, PLAN_TYPE, PLAN_DURATION,PLAN_PRICE,PLAN_DESCRIPTION,IMAGE_URL) 
	VALUES (01, 'PLATINUM',12*30,500, 'Premium twelve-month plan','http://localhost:8080/api/images/get?name=platinum.png');
INSERT INTO PLANS (PLAN_ID, PLAN_TYPE, PLAN_DURATION,PLAN_PRICE,PLAN_DESCRIPTION,IMAGE_URL) 
	VALUES (02, 'GOLD',6*30,270, 'Standard six-month plan','http://localhost:8080/api/images/get?name=gold.png');
INSERT INTO PLANS (PLAN_ID, PLAN_TYPE, PLAN_DURATION,PLAN_PRICE,PLAN_DESCRIPTION,IMAGE_URL) 
	VALUES (03, 'BRONZE',1*30,50, 'Standard one-month plan','http://localhost:8080/api/images/get?name=bronze.png');
    
INSERT INTO USERS (USER_ID,ROLE,FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,PLAN_ID,IMAGE_URL,REGISTERED_DATE) 
	VALUES (01,'ADMIN','Natalia ','Giourda','giourda_nat@gmail.gr','$2a$10$FuPmtewWmSDeRbHHXmX6DelfZ5poJc16J5Mio6atxRd3xut4Kkgru','Evelpidon 25',01,'http://localhost:8080/api/images/get?name=giourda.jpg','2022-04-1');

INSERT INTO USERS (USER_ID, ROLE,FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,PLAN_ID,IMAGE_URL,REGISTERED_DATE) 
	VALUES (02, 'USER','Giorgos','Papadopoulos','papadopg@hotmail.com','$2a$10$L500bcdf3NPuhb8ztIQEu.cmMANVpj2w.UaR3bS20pfI.qWqdyB2O','Venizelou 103',02,'http://localhost:8080/api/images/get?name=papadopoulos.jpg','2022-12-6');
INSERT INTO USERS (USER_ID, ROLE,FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,PLAN_ID,IMAGE_URL,REGISTERED_DATE) 
	VALUES (03, 'USER','Manolis','Konstantinou','emmanuel_K@gmail.gr','$2a$10$Av24ZEXST54CChK72qlEaumYmf3zfTIZZ/yWhv6Ym9bNm3IwmqQ4i','Athanasiou Diakou 56',03,'http://localhost:8080/api/images/get?name=konstantinou.jpg','2023-01-25');
INSERT INTO USERS (USER_ID, ROLE,FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,PLAN_ID,IMAGE_URL,REGISTERED_DATE) 
	VALUES (04, 'USER','Christos','Apostolou','chrapos@hotmail.com','$2a$10$mA3rUwBNlMZ3rEsQphxDU.b3Ti/Tpy57wXo7dHEPhVk35zTBwscJC','Iera odos 205',02,'http://localhost:8080/api/images/get?name=apostolou.jpg','2022-12-1');
INSERT INTO USERS (USER_ID, ROLE,FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,PLAN_ID,IMAGE_URL,REGISTERED_DATE) 
	VALUES (05, 'USER','Maria','Andreou','maria_andreou83@gmail.gr','$2a$10$hV6k9fhuM9xXJVjabQClyOd6O2eWtR/cP86zO1sqjRcY6mlSDkoKO','Gedeon 43',01,'http://localhost:8080/api/images/get?name=andreou.jpg','2022-07-12');
INSERT INTO USERS (USER_ID, ROLE,FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,PLAN_ID,IMAGE_URL,REGISTERED_DATE) 
	VALUES (06, 'USER','Christina','Georgiou','ch.geor@hotmail.com','$2a$10$VHPIBW5ZXNM/FD/RCDWSk.RbfcajXBQcAGbptA0.RdpxsVQ03Z7oS','Kolokotroni 4',02,'http://localhost:8080/api/images/get?name=georgiou.jpg','2023-01-12');

INSERT INTO INSTRUCTORS (INSTRUCTOR_ID, INSTRUCTOR_NAME, INSTRUCTOR_LASTNAME,INSTRUCTOR_EMAIL,IMAGE_URL,INSTRUCTOR_SPECIALTY) 
	VALUES (01,'Konstantinos','Georgiou','kostas_georgiou@hotmail.com','http://localhost:8080/api/images/get?name=kostas_georgiou.jpg','Trainer');
INSERT INTO INSTRUCTORS (INSTRUCTOR_ID, INSTRUCTOR_NAME, INSTRUCTOR_LASTNAME,INSTRUCTOR_EMAIL,IMAGE_URL,INSTRUCTOR_SPECIALTY) 
	VALUES (02,'Anna','Stergianou','anna_stergianou@gmail.gr','http://localhost:8080/api/images/get?name=anna_stergianou.jpg','Yoga Instructor');
INSERT INTO INSTRUCTORS (INSTRUCTOR_ID, INSTRUCTOR_NAME, INSTRUCTOR_LASTNAME,INSTRUCTOR_EMAIL,IMAGE_URL,INSTRUCTOR_SPECIALTY) 
	VALUES (03,'Giorgos','Athinaiou','giorgosA@hotmail.com','http://localhost:8080/api/images/get?name=giorgos_athinaiou.jpg','Crossfit Trainer');
INSERT INTO INSTRUCTORS (INSTRUCTOR_ID, INSTRUCTOR_NAME, INSTRUCTOR_LASTNAME,INSTRUCTOR_EMAIL,IMAGE_URL,INSTRUCTOR_SPECIALTY) 
	VALUES (04,'Dimitra','Anagnostou','dimitra_anagnostou@gmail.gr','http://localhost:8080/api/images/get?name=dimitra_anagnostou.jpg','Trainer');
 
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (01, 'Pilates','Pilates is a type of exercise that emphasizes the use of the core muscles to improve strength, flexibility, and balance. It was developed in the early 20th century by Joseph Pilates and is typically performed on a mat or using specialized equipment such as the Reformer. Pilates exercises focus on controlled movements, proper alignment, and breathing techniques, with an emphasis on developing a strong "powerhouse" or core. It is considered a low-impact form of exercise and can be beneficial for people of all ages and fitness levels.','http://localhost:8080/api/images/get?name=pilates.jpg',04);
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (02, 'Caribbean Beat','Caribbean Beat is an exciting gym course that takes inspiration from the lively and energetic rhythms of the Caribbean. This course is designed to get your heart pumping and your body moving as you engage in a variety of exercises and dance routines set to the upbeat and infectious beats of soca, reggae, and dancehall music. Participants can expect to sweat and burn calories while enjoying the vibrant and dynamic atmosphere of this unique workout experience. Caribbean Beat is perfect for those who want to have fun while getting fit and improving their overall health and well-being.','http://localhost:8080/api/images/get?name=caribbean_beat.jpg',02);
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (03, 'Yoga','Yoga is a physical, mental, and spiritual practice that originated in ancient India. It includes a variety of techniques, including physical postures (asanas), breathing exercises (pranayama), meditation, and the adoption of specific ethical and moral principles (yamas and niyamas). Yoga aims to unite the mind, body, and spirit and promote overall well-being. It is a low-impact form of exercise that can help improve flexibility, strength, balance, and relaxation. Yoga can be practiced by people of all ages and fitness levels and can be adapted to suit individual needs and abilities. It is also used as a complementary therapy to treat a wide variety of health conditions.','http://localhost:8080/api/images/get?name=yoga.jpg',04);
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (04, 'Power Body & Fat Burning','Power body and fat burning are terms that are often used to describe fitness programs or classes that focus on building strength and muscle mass, while also promoting weight loss and burning fat. These types of workouts typically involve high-intensity, full-body exercises that incorporate resistance training, cardio, and functional movements. This type of workout is designed to increase the heart rate, burn calories and target various muscle groups. These exercises may include weightlifting, bodyweight exercises, plyometrics, and circuit training. Power body and fat burning programs are intended to improve overall fitness and physique, while also promoting fat loss and weight management. They are often goal-oriented and results-driven, and are suitable for people of all fitness levels.','http://localhost:8080/api/images/get?name=power_body.jpg',02);
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (05, 'Personal Training','Personal training is a form of one-on-one fitness instruction that is designed to help individuals reach their specific fitness goals. Personal trainers work with clients to develop customized exercise programs that are tailored to their individual needs and abilities. These programs may include a combination of cardiovascular exercise, strength training, flexibility work, and other forms of physical activity.Personal trainers assess their clients fitness level, medical history, and goals, and then develop a program to help them achieve their objectives. They also provide guidance on proper form and technique, and monitor progress to ensure that the client is meeting their goals. Personal trainers also teach their clients how to set realistic and measurable goals, provide them with motivational support and help them make lifestyle changes that will promote overall wellness. Personal training is available in various settings, including gyms, health clubs, and clients homes.','http://localhost:8080/api/images/get?name=personal_training.jpg',03);
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (06, 'Art Fitness','Art fitness classes often involve choreographed routines set to music, and may include elements of ballet, jazz, hip-hop, or other dance styles. It also includes activities such as pole dance, aerial yoga, and circus arts, which blend fitness with artistic expression and performance. These classes can be a fun and engaging way to stay active and improve overall fitness, while also providing an outlet for creativity and self-expression.','http://localhost:8080/api/images/get?name=art_fitness.jpg',01);
INSERT INTO COURSES (COURSE_ID, COURSE_NAME, COURSE_DESCRIPTION,IMAGE_URL,INSTRUCTOR_ID) 
	VALUES (07, 'Exercise During Pregnancy','The goal of these classes is to help pregnant women maintain their fitness and well-being during pregnancy, while also preparing them for the physical demands of labor and delivery. They may also provide a supportive community for pregnant women to connect and share their experiences with others who are going through a similar experience.','http://localhost:8080/api/images/get?name=prenatal.jpg',04);

INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,01);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,02);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,03);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,04);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,05);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,06);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (01,07);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (02,01);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (02,02);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (02,03);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (02,04);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (03,01);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (03,02);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (03,03);
INSERT INTO PLANS_COURSES (PLAN_ID, COURSE_ID)
	VALUES (03,04);
    
INSERT INTO SCHEDULE(schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (01, 'MONDAY',"17:00","18:00","B.01",02);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (02, 'MONDAY',"17:00","18:00","A.01",01);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (03, 'MONDAY',"18:00","20:00","A.01",04);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (04, 'MONDAY',"20:00","21:00","A.03",07);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (05, 'TUESDAY',"17:00","18:00","A.01",04);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (06, 'TUESDAY',"18:00","20:00","A.01",03);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (07, 'TUESDAY',"21:00","22:00","A.02",05);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (08, 'TUESDAY',"21:00","22:00","B.02",01);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (09, 'TUESDAY',"21:00","22:00","A.02",01);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (10, 'WEDNESDAY',"19:00","21:00","A.01",02);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (11, 'WEDNESDAY',"18:00","19:00","B.01",03);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (12, 'WEDNESDAY',"17:00","18:00","A.01",06);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (13, 'WEDNESDAY',"21:00","23:00","A.02",05);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (14, 'THURSDAY',"17:00","18:00","A.03",07);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (15, 'THURSDAY',"18:00","19:00","B.02",06);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (16, 'THURSDAY',"19:00","20:00","A.02",03);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (17, 'THURSDAY',"20:00","21:00","A.01",04);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (18, 'THURSDAY',"17:00","18:00","A.01",01);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (19, 'FRIDAY',"17:00","19:00","A.01",06);
INSERT INTO SCHEDULE (schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (20, 'FRIDAY',"21:00","23:00","A.01",05);
INSERT INTO SCHEDULE(schedule_id,scheduled_day, scheduled_start_time, scheduled_end_time,scheduled_room,course_id) 
	VALUES (21, 'FRIDAY',"21:00","22:00","A.02",01);

INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (01,'bronze.png','image/png');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (02,'gold.png','image/png');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (03,'platinum.png','image/png');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (04,'andreou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (05,'apostolou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (06,'georgiou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (07,'giourda.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (08,'konstantinou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (09,'papadopoulos.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (10,'anna_stergianou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (11,'dimitra_anagnostou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (12,'giorgos_athinaiou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (13,'kostas_georgiou.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (14,'art_fitness.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (15,'caribbean_beat.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (16,'personal_training.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (17,'pilates.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (18,'power_body.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (19,'prenatal.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (20,'yoga.jpg','image/jpeg');
INSERT INTO IMAGES (ID,NAME,TYPE) 
	VALUES (21,'no_image.png','image/jpeg');
    
INSERT INTO EVENTS (event_id,user_id,course_id,start_timestamp,end_timestamp) 
	VALUES (01,01,01,1674594000,1674597600);
INSERT INTO EVENTS (event_id,user_id,course_id,start_timestamp,end_timestamp) 
	VALUES (02,01,01,1674752400,1674756000);
INSERT INTO EVENTS (event_id,user_id,course_id,start_timestamp,end_timestamp) 
	VALUES (03,01,06,1674666000,1674669600);
 
SELECT * FROM PLANS;
SELECT * FROM USERS;
SELECT * FROM INSTRUCTORS;
SELECT * FROM COURSES;
SELECT * FROM PLANS_COURSES;
SELECT * FROM SCHEDULE;
SELECT * FROM EVENTS;
SELECT * FROM IMAGES;