# Roles
insert into roles values ( 'super_admin', 'Super administrator' );
insert into roles values ( 'admin', 'Administrator' );
insert into roles values ( 'u_student', 'Undergraduate student' );
insert into roles values ( 'p_student', 'Postgraduate student' );
insert into roles values ( 'external', 'External' );

# Users / pass: 123456
insert into users values ( 'tony_stark' , 'fHgw+qmoU3sqLYRCZF4S564F5CiTM+LuvSafPka+u6RJ1oMQDCuB1fGKJLm5BvDl', 'Tony', 'Stark', '6971234567', 'tony_stark@unipi.gr', 'super_admin');
insert into users values ( 'natasha_romanoff' , 'fHgw+qmoU3sqLYRCZF4S564F5CiTM+LuvSafPka+u6RJ1oMQDCuB1fGKJLm5BvDl', 'Natasha', 'Romanoff', '6971234567', 'natasha_romanoff@unipi.gr', 'admin');
insert into users values ( 'bruce_banner' , 'fHgw+qmoU3sqLYRCZF4S564F5CiTM+LuvSafPka+u6RJ1oMQDCuB1fGKJLm5BvDl', 'Bruce', 'Banner', '6979876543', 'bruce_banner@unipi.gr', 'u_student');
insert into users values ( 'peter_parker' , 'fHgw+qmoU3sqLYRCZF4S564F5CiTM+LuvSafPka+u6RJ1oMQDCuB1fGKJLm5BvDl', 'Peter', 'Parker', '6973698541', 'peter_parker@unipi.gr', 'u_student');
insert into users values ( 'thor_odinson' , 'fHgw+qmoU3sqLYRCZF4S564F5CiTM+LuvSafPka+u6RJ1oMQDCuB1fGKJLm5BvDl', 'Thor', 'Odinson', '6941234567', 'thor_odinson@unipi.gr', 'p_student');
insert into users values ( 'steve_rogers' , 'fHgw+qmoU3sqLYRCZF4S564F5CiTM+LuvSafPka+u6RJ1oMQDCuB1fGKJLm5BvDl', 'Steve', 'Rogers', '6931478529', 'steve_rogers@ibm.gr', 'external');

# Departments
insert into departments values ( 'comp_sc', 'Computer Science' );
insert into departments values ( 'econ', 'Economics' );

# User departments
insert into user_department values ( 'tony_stark', 'comp_sc' );
insert into user_department values ( 'natasha_romanoff', 'comp_sc' );
insert into user_department values ( 'bruce_banner', 'comp_sc' );
insert into user_department values ( 'peter_parker', 'comp_sc' );

# Companies
insert into companies values ( 'ibm', 'IBM', 'Messogion Str.', '2101234567', 'ibmcorp@ibm.com', 'www.ibm.com' );
insert into companies values ( 'oracle', 'Oracle', 'Athinon Str.', '2109652365', 'oraclecorp@oracle.com', 'www.oracle.com' );
insert into companies values ( 'ms', 'Microsoft', 'Syggrou Str.', '2119854122', 'mscorp@microsoft.com', 'www.microsoft.com' );

# User companies
insert into user_company values ( 'steve_rogers', 'ibm' );

# CV
insert into cvs( username, file_url) values ( 'bruce_banner', '' );
insert into cvs( username, file_url) values ( 'peter_parker', '' );
insert into cvs( username, file_url) values ( 'thor_odinson', '' );


#Keywords
insert into keywords(title, slug) values( 'Java', 'java' );
insert into keywords(title, slug) values( 'C++', 'cpp' );
insert into keywords(title, slug) values( 'HTML', 'html' );
insert into keywords(title, slug) values( 'CSS', 'css' );

#Keyword CV
insert into keyword_cv value (1,1);
insert into keyword_cv value (2,1);
insert into keyword_cv value (3,1);

#Categories
insert into categories(title, slug) values( 'Backend', 'backend' );
insert into categories(title, slug) values( 'Frontend', 'frontend' );
insert into categories(title, slug) values( 'Data Analysis', 'data_anal' );

# Classifieds
insert into classifieds(title, content, company_id, category_id) values( 'Frontend developer', 'mpla mpla mpla', 'ibm', 2 );
insert into classifieds(title, content, company_id, category_id) values( 'Backend developer', 'taka taka taka', 'ms', 1 );

#Keyword_classified
insert into keyword_classified value (1,1);
insert into keyword_classified value (2,1);
insert into keyword_classified value (2,2);
