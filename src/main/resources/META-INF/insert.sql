--------------------------------------------------------
--  Inserting AirlineCompanies
--------------------------------------------------------
INSERT INTO airlinecompany (id,version,available,name) VALUES (10,1,TRUE ,'Jetairfly');
INSERT INTO airlinecompany (id,version,available,name) VALUES (20,1,TRUE,'British Airways');
INSERT INTO airlinecompany (id,version,available,name) VALUES (30,1,TRUE,'Thomas Coock Airlines');
INSERT INTO airlinecompany (id,version,available,name) VALUES (40,1,TRUE,'ABX Air');
commit;

--------------------------------------------------------
--  Inserting Regions
--------------------------------------------------------
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (0,null,1,'Pacific');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (1,null,1,'America');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (2,null,1,'Atlantic');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (3,null,1,'Africa');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (4,null,1,'Europe');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (5,null,1,'Arctic');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (6,null,1,'Indian');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (7,null,1,'Asia');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (9,null,1,'Australia');
INSERT INTO region (id,lastUpdateDate,version,name) VALUES (10,null,1,'Antarctica');
COMMIT;

--------------------------------------------------------
--  Inserting Airports
--------------------------------------------------------
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (100,null,1,TRUE,'YOW','Canada','Ottawa Macdonald Cartier Intl',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (200,null,1,TRUE,'YZT','Canada','Port Hardy',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (400,null,1,TRUE,'KSF','Germany','Kassel Calden',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (500,null,1,TRUE,'ESH','United Kingdom','Shoreham',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (600,null,1,TRUE,'NOC','Ireland','Ireland West Knock',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (800,null,1,TRUE,'ELS','South Africa','East London',3);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (900,null,1,TRUE,'NGE','Cameroon','Ngaoundere',3);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1000,null,1,TRUE,'FYT','Chad','Faya Largeau',3);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1100,null,1,TRUE,'FAA','Guinea','Faranah',3);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1200,null,1,TRUE,'DBV','Croatia','Dubrovnik',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1300,null,1,TRUE,'RCO','France','St Agnant',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1500,null,1,TRUE,'CRV','Italy','Crotone',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1800,null,1,TRUE,'CZM','Mexico','Cozumel Intl',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (1900,null,1,TRUE,'AVI','Cuba','Maximo Gomez',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (2000,null,1,TRUE,'LIF','New Caledonia','Lifou',0);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (2300,null,1,TRUE,'OIR','Japan','Okushiri',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (2400,null,1,TRUE,'CGY','Philippines','Cagayan De Oro',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (2500,null,1,TRUE,'RZA','Argentina','Santa Cruz',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (2800,null,1,TRUE,'CHH','Peru','Chachapoyas',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (2900,null,1,TRUE,'AXA','Anguilla','Wallblake',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3000,null,1,TRUE,'IXG','India','Belgaum',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3100,null,1,TRUE,'IXJ','India','Jammu',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3200,null,1,TRUE,'NHA','Vietnam','Nhatrang',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3300,null,1,TRUE,'KTE','Malaysia','Kerteh',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3400,null,1,TRUE,'HRB','China','Taiping',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3500,null,1,TRUE,'MXF','United States','Maxwell Afb',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3600,null,1,TRUE,'LOU','United States','Bowman Fld',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3700,null,1,TRUE,'ACT','United States','Waco Rgnl',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3800,null,1,TRUE,'FLV','United States','Sherman Aaf',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (3900,null,1,TRUE,'PCB','Indonesia','Pondok Cabe',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (4000,null,1,TRUE,'URT','Thailand','Surat Thani',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (4100,null,1,TRUE,'IDA','United States','Idaho Falls Rgnl',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (4200,null,1,TRUE,'BXU','Philippines','Butuan',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (4300,null,1,TRUE,'CJM','Thailand','Chumphon',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (5500,null,1,TRUE,'YHC','Canada','Vancouver Harbour Water Airport',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (5600,null,1,TRUE,'HIL','Ethiopia','Shilavo Airport',3);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (5700,null,1,TRUE,'ATB','Sudan','Atbara Airport',3);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (5800,null,1,TRUE,'ADF','Turkey','Adiyaman Airport',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (5900,null,1,TRUE,'PBJ','Vanuatu','Tavie Airport',0);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6000,null,1,TRUE,'ONJ','Japan','Odate Noshiro Airport',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6100,null,1,TRUE,'UJE','Marshall Islands','Ujae Atoll Airport',0);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6200,null,1,TRUE,'MNU','Burma','Mawlamyine Airport',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6300,null,1,TRUE,'MRZ','Australia','Moree Airport',9);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6400,null,1,TRUE,'MIG','China','Mianyang Airport',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6500,null,1,TRUE,'MCW','United States','Mason City Municipal',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (6800,null,1,TRUE,'ESC','United States','Delta County Airport',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (7000,null,1,TRUE,'MLD','United States','Malad City',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (7100,null,1,TRUE,'KEK','United States','Ekwok Airport',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (7200,null,1,TRUE,'SXP','United States','Sheldon Point Airport',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (7500,null,1,TRUE,'ZAJ','Afghanistan','Zaranj Airport',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (7700,null,1,TRUE,'PHA','Vietnam','Phan Rang Airport',7);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (7800,null,1,TRUE,'ESX','Germany','Essen HBF',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (8000,null,1,TRUE,'GML','Ukraine','Gostomel Antonov',4);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (8200,null,1,TRUE,'OOK','United States','Toksook Bay Airport',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (8300,null,1,TRUE,'CTJ','United States','West Georgia Regional Airport - O V Gray Field',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (8800,null,1,TRUE,'CDK','United States','CedarKey',1);
INSERT INTO airport (id,lastUpdateDate,version,available,code,country,name,region_id) VALUES (9200,null,1,TRUE,'X26','United States','Sebastian Municipal',1);
commit;

--------------------------------------------------------
--  Inserting Flights
--------------------------------------------------------
INSERT INTO flight (id,lastUpdateDate,version,arrivalTime,departureTime,number,airlineCompany_id,arrival_id,departure_id) VALUES (200,null,1,{ts '2017-05-05 19:15:10.'},{ts '2017-05-05 18:00:00.'},'ASI-1234',10,9200,8000);
INSERT INTO flight (id,lastUpdateDate,version,arrivalTime,departureTime,number,airlineCompany_id,arrival_id,departure_id) VALUES (300,null,1,{ts '2017-05-10 20:00:10.'},{ts '2017-05-10 18:30:00.'},'ASI-1234',10,null,null);
INSERT INTO flight (id,lastUpdateDate,version,arrivalTime,departureTime,number,airlineCompany_id,arrival_id,departure_id) VALUES (400,null,1,{ts '2017-06-05 19:15:10.'},{ts '2017-06-05 18:00:00.'},'ASI-1234',20,null,null);
INSERT INTO flight (id,lastUpdateDate,version,arrivalTime,departureTime,number,airlineCompany_id,arrival_id,departure_id) VALUES (500,null,1,{ts '2017-06-10 20:00:10.'},{ts '2017-06-10 18:30:00.'},'ASI-1234',20,null,null);
commit;

--------------------------------------------------------
--  Inserting travelclasses
--------------------------------------------------------
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (200100,null,1,110.00,'Economy',null,55,200);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (200200,null,1,120.00,'Business',null,5,200);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (300100,null,1,100.00,'Economy',null,50,300);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (300200,null,1,110.00,'Business',null,5,300);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (300300,null,1,120.00,'First Class',null,10,300);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (400100,null,1,100.00,'Economy',null,100,400);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (400200,null,1,150.00,'Business',null,100,400);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (500100,null,1,100.00,'Economy',null,100,500);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (500200,null,1,150.00,'Business',null,100,500);
INSERT INTO travelclass (id,lastUpdateDate,version,basePrice,name,overriddenPrice,remainingSeats,flight_id) VALUES (500300,null,1,200.00,'First Class',null,50,500);
