create table bhaskarb_ebpp_customer_profile_tbl(email varchar(100),name varchar(40), password varchar(100))
select * from bhaskarb_ebpp_customers_list_tbl
delete from bhaskarb_ebpp_customers_list_tbl where email='y11acs412@gmail.com'

create table bhaskarb_ebpp_customer_previous_bills_tbl(email varchar(100),month varchar(15), billno varchar(20),amount varchar(20),payoption varchar(30),payoptiondetails varchar(30),paymentdate TIMESTAMP)

select * from bhaskarb_ebpp_billsDetails_tbl
 email                     month      billnumber     amount     duedate    
 ------------------------  ---------  -------------  ---------  ---------- 
 y11acs412@gmail.com       August     111111         1500       20/9/2017  
 battubhaskar24@gmail.com  August     111112         2500       19/9/2017  
 y11acs412@gmail.com       September  111113         1600       21/9/2017  
 battubhaskar24@gmail.com  September  111114         2200       22/9/2017 


select cl.name from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl where bd.email=cl.email
select bd.email,cl.name,sum(bd.amount), bd.duedate from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl where bd.email=cl.email group by bd.email


select * from bhaskarb_ebpp_customers_list_tbl
select * from bhaskarb_ebpp_customers_list_tbl
select * from bhaskarb_ebpp_customers_list_tbl where email like '%e%' or name like '%e%'

delete from bhaskarb_ebpp_customers_list_tbl where email='ashajyoti@gmail.com'
commit
select * from bhaskarb_ebpp_billsDetails_tbl
delete from bhaskarb_ebpp_billsDetails_tbl where email='ashajyoti@gmail.com'
select cl.name,bd.email,bd.amount,bd.billnumber,bd.month, bd.duedate from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl where bd.email=cl.email order by bd.email
select bd.email,cl.name,sum(bd.amount), bd.duedate from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl where bd.email=cl.email group by bd.email

select bd.email,cl.name from bhaskarb_ebpp_billsDetails_tbl as bd, bhaskarb_ebpp_customers_list_tbl as cl where bd.email=cl.email and ( bd.email like '%g%' or cl.name like '%g%' ) group by bd.email
