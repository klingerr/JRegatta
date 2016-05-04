insert into Club (id, short_Name, name, adress)
  values (1, 'AdW', 'Akademie der Wissenschaften', 'Zeuthen, Platanenalle');

insert into Club (id, short_Name, name, adress)
  values (2, 'Argo', null, null);



insert into Regatta (id, short_name, name, start_date, end_date, finished, buoyages)
  values(1, '1. Krümel', 'Die 1. Krümelregatta', '2014-05-01', '2014-05-02', false, 4);
 
insert into Regatta (id, name, short_name, start_date, end_date, finished, buoyages)
  values(2, '2. Krümel', 'Die 2. Krümelregatta', '2015-05-04', '2015-05-04', true, 5);
  
insert into Regatta (id, name, short_name, start_date, end_date, finished, buoyages)
  values(3, '3. Krümel', 'Die 3. Krümelregatta', '2016-05-05', '2016-05-06', false, 6);

  
-- 1. regatta  
insert into Skipper (id, sail_Number, first_Name, last_Name, birth_Day, gender, age_Group, catering, entry_Fee, late_Registration, lunch, regatta_id, Club_id)
  values(1, '4711', 'Ralf', 'Klinger', '1969-07-02', 'M', 'AK10', true, true, false, true, 1, 1);
  
insert into Skipper (id, sail_Number, first_Name, last_Name, birth_Day, gender, age_Group, catering, entry_Fee, late_Registration, lunch, regatta_id, Club_id)
  values(2, '0815', 'Maggie', 'Germer', '1967-01-03', 'W', 'AK9', true, true, false, true, 1, 2);
  
-- 2. regatta  
insert into Skipper (id, sail_Number, first_Name, last_Name, birth_Day, gender, age_Group, catering, entry_Fee, late_Registration, lunch, regatta_id, Club_id)
  values(3, '123456', 'Klaus', 'Brause', '1981-08-12', 'M', 'AK10', true, true, false, true, 2, 1);
  
  
  
-- 1. regatta  
insert into Race(id, number, start_time, end_time, regatta_id)
  values(1, 1, '11:00', '11:45', 1);        

insert into Race(id, number, start_time, end_time, regatta_id)
  values(2, 2, '11:55', '12:35', 1);        

insert into Race(id, number, start_time, end_time, regatta_id)
  values(3, 3, '11:00', '11:45', 1);        

  
-- 2. regatta  
insert into Race(id, number, start_time, end_time, regatta_id)
  values(4, 1, '10:00', '10:30', 2);        

insert into Race(id, number, start_time, end_time, regatta_id)
  values(5, 2, '10:45', '11:42', 2);        

insert into Race(id, number, start_time, end_time, regatta_id)
  values(6, 3, '12:15', '13:33', 2);        


-- 1. regatta  
-- 1. race
insert into Result (id, race_id, skipper_id, placement, points)
  values (1, 1, 1, '1', 1);
        
insert into Result (id, race_id, skipper_id, placement, points)
  values (2, 1, 2, '2', 2);
        
-- 2. race
insert into Result (id, race_id, skipper_id, placement, points)
  values (3, 2, 2, '1', 1);
        
insert into Result (id, race_id, skipper_id, placement, points)
  values (4, 2, 1, '2', 2);

-- 3. race
insert into Result (id, race_id, skipper_id, placement, points)
  values (5, 3, 2, '1', 1);
        
insert into Result (id, race_id, skipper_id, placement, points)
  values (6, 3, 1, 'DNF', 3);
