insert into Regatta (id, name, short_name, start_date, end_date, finished, buoyages)
  values(1, '1. Krümel', 'Die 1. Krümelregatta', null, null, false, 4);
 
insert into Regatta (id, name, short_name, start_date, end_date, finished, buoyages)
  values(2, '2. Krümel', 'Die 2. Krümelregatta', null, null, true, 5);
  
insert into Regatta (id, name, short_name, start_date, end_date, finished, buoyages)
  values(3, '3. Krümel', 'Die 3. Krümelregatta', null, null, false, 6);
  

  
insert into Club (id, short_Name, name, adress)
  values (1, 'AdW', 'Akademie der Wissenschaften', 'Zeuthen, Platanenalle');

insert into Club (id, short_Name, name, adress)
  values (2, 'Argo', null, null);

  
  
insert into Skipper (id, sail_Number, first_Name, last_Name, birth_Date, sex, age_Group, catering, entry_Fee, late_Registration, lunch, regatta_id, Club_id)
  values(1, '4711', 'Ralf', 'Klinger', '1969-07-02', 'M', 'AK10', true, true, false, true, 1, 1);
  
insert into Skipper (id, sail_Number, first_Name, last_Name, birth_Date, sex, age_Group, catering, entry_Fee, late_Registration, lunch, regatta_id, Club_id)
  values(2, '0815', 'Maggie', 'Germer', '1967-01-03', 'W', 'AK9', true, true, false, true, 1, 2);
  
  
  
insert into Race(id, number, start_time, end_time, regatta_id)
  values(1, 1, '11:00', '11:45', 1);        

insert into Race(id, number, start_time, end_time, regatta_id)
  values(2, 2, '11:55', '12:35', 1);        

insert into Race(id, number, start_time, end_time, regatta_id)
  values(3, 3, '11:00', '11:45', 1);        

  

insert into Result (id, race_id, skipper_id, placement, points)
  values (1, 1, 1, '1', 1);
        
insert into Result (id, race_id, skipper_id, placement, points)
  values (2, 1, 2, '2', 2);
        
