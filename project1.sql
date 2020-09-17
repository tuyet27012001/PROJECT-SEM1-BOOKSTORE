create database Bookstore;

use Bookstore;
create table Customers(
	customer_id int auto_increment primary key,
	customer_name char(150) not null,
	phonenumber char(12) ,
	gender char(5),
	birth_date date,
	email char(100) unique,
	password_customer char(250) not null
);

create table Delivery_addresses(
	address_id int auto_increment primary key,
	name_customer char(100) not null,
	phonenumber char(12) not null,
	city char(50) not null,
	district char(50) not null,
	address char(200),
    address_status char(10),
    address_default char(15),
	customer_id int not null,
	constraint address_customer foreign key (customer_id) references Customers(customer_id)
);

create table Payment_methods(
	payment_method_id int auto_increment primary key,
	payment_method_name char(100) not null unique
);

insert into Payment_methods (payment_method_name ) values 
('Thanh toan khi nhan hang'),
('Thanh toan bang the quoc te Visa , Master, JCB'),
('The ATM noi dia / Internet Banking');

delimiter //
create procedure display_payment_methods()
begin 
	select *from Payment_methods;
end // 
delimiter ;

create table Shipping_units(
	shipping_unit_id int auto_increment primary key,
	shipping_unit_name char(100) not null unique,
	shippingFee double check (shippingFee >= 0),
	shipping_date char(100) 
);

insert into Shipping_units( shipping_unit_name ,shippingFee ,shipping_date ) values 
    ('Giao hang tiet kiem ', 15000, '2-3 ngay'),
    ('Giao hang nhanh', 20000, '1-2 ngay'),
    ('Viettel Post' , 18000, '3-4 ngay');

delimiter //
create procedure display_shipping_unit()
begin 
	select *from Shipping_units;
end // 
delimiter ;

create table Orders(
	order_id int auto_increment primary key,
	date_time datetime not null default  current_timestamp,
	payment_method_id int not null,
	shipping_unit_id int not null,
	address_id int not null,
    order_status char(100) not null,
	constraint order_payment foreign key (payment_method_id) references Payment_methods(payment_method_id),
	constraint order_shipping foreign key (shipping_unit_id) references Shipping_units(shipping_unit_id),
	constraint order_address foreign key (address_id) references Delivery_addresses(address_id)
);

create table Publishing_Companies(
	publishing_company_id int auto_increment primary key,
	publishing_company_name char(100) not null unique
);

create table Categories(
	category_id int auto_increment primary key,
	category_name char(100) not null unique
);

create table Books(
	book_id int auto_increment primary key,
    publishing_company_id int not null,
	title char(100) not null,
    author char(100),
    price double check (price >= 0),
	detail char(250),
    quantity int not null check(quantity >= 0),
    isbn char(50),
	constraint Publishing_Company_Book foreign key (publishing_company_id) references Publishing_Companies(publishing_company_id)
);


create table Categories_Books(
	book_id int not null,
	category_id int not null,
	constraint category_book_1 foreign key (book_id) references Books(book_id),
	constraint category_book_2 foreign key (category_id) references Categories(category_id)
);


create table Orders_Books(
	book_id int not null,
    order_id int not null,
	quantity int check (quantity > 0),
	price double check(price >= 0),
	sale double check(sale > 0),
	constraint order_book_1 foreign key (order_id) references Orders(order_id),
	constraint order_book_2 foreign key (book_id) references Books(book_id)
);


create table Sales_off(
	sale_id int auto_increment primary key,
    book_id int not null,
    sale double not null,
    start_time datetime,
    end_time datetime,
	constraint sale_book_2 foreign key (book_id) references Books(book_id)
); 

delimiter //
create procedure update_quantity_book(In id int, quantity1 int)
begin 
update Books set quantity = quantity1
where book_id = id;
end // 
delimiter ;

delimiter //
create procedure id_order()
begin 
	select *from Orders
    ORDER BY order_id  DESC
    limit 1;
end // 
delimiter ;

delimiter //
create procedure id_address(In id int)
begin 
	select *from Delivery_addresses
    where customer_id = id
    ORDER BY address_id  DESC
    limit 1;
end // 
delimiter ;


delimiter //
create procedure display_category()
begin 
	select category_id, category_name
	from Categories
    order by category_id asc;
end // 
delimiter ;

delimiter //
create procedure orderListBook(In id int)
begin 
	select *from Orders_Books
    where order_id = id;
end // 
delimiter ;

delimiter //
create procedure display_book()
begin 
	select b.book_id ,p.publishing_company_name ,b.title , b.author ,b.price ,b.detail ,b.quantity ,b.isbn 
	from Books b inner join Publishing_Companies p on b.publishing_company_id = p.publishing_company_id
    order by b.book_id asc;
end // 
delimiter ;

delimiter //
create procedure detail_book(In id int)
begin 
	select b.book_id ,p.publishing_company_name ,b.title , b.author ,b.price ,b.detail ,b.quantity ,b.isbn 
	from Books b inner join Publishing_Companies p on b.publishing_company_id = p.publishing_company_id
    where b.book_id = id;
end // 
delimiter ;


delimiter //
create procedure display_customer()
begin 
	select *
	from Customers;
end // 
delimiter ;

delimiter //
create procedure insert_customer(In  customerName varchar(100) , gender char(100) , birthday date, phone char(200), email char(100), pass char(50))
begin 
insert into Customers( customer_name ,gender,birth_date ,phonenumber, email, password_customer) values(customerName, gender,  birthday, phone, email, pass);
end // 
delimiter ;

delimiter //
create procedure insert_order(In date_time1 datetime ,payment_method_id1 int, shipping_unit_id1 int,address_id1 int,order_status1 char(100))
begin 
insert into Orders( date_time ,payment_method_id, shipping_unit_id ,address_id ,order_status) values( date_time1 ,payment_method_id1, shipping_unit_id1 ,address_id1 ,order_status1);
end // 
delimiter ;

delimiter //
create procedure order_detail(In bookId int, orderId int ,quantityBook int ,priceBook double )
begin 
insert into Orders_Books( book_id ,order_id, quantity, price ) values( bookId ,orderId,quantityBook ,priceBook);
end // 
delimiter ;


delimiter //
create procedure search_phone_pass_customer(In phone varchar(100), pass varchar(250))
begin 
select *from Customers
where phonenumber = phone and password_customer = pass;
end // 
delimiter ;

delimiter //
create procedure search_email_pass_customer(In email1 varchar(100), pass varchar(250))
begin 
select *from Customers
where email = email1 and password_customer = pass;
end // 
delimiter ;

delimiter //
create procedure detail_customer(In id int)
begin 
select *from Customers b
where b.customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_name_customer(In id int, nameCustomer char(100))
begin 
update Customers set customer_name = nameCustomer
where customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_phone_customer(In id int, phoneCustomer char(100))
begin 
update Customers set phonenumber = phoneCustomer
where customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_email_customer(In id int, emailCustomer char(100))
begin 
update Customers set email = emailCustomer
where customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_gender_customer(In id int, genderCustomer char(100))
begin 
update Customers set gender = genderCustomer
where customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_birth_customer(In id int, birth char(100))
begin 
update Customers set birth_date = birth
where customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_pass_customer(In id int, pass char(250))
begin 
update Customers set  password_customer = pass
where customer_id = id;
end // 
delimiter ;

delimiter //
create procedure update_name_address(In id int, nameCustomer char(250))
begin 
update Delivery_addresses set  name_customer = nameCustomer
where address_id = id;
end // 
delimiter ;

delimiter //
create procedure update_phone_address(In id int, phoneCustomer char(250))
begin 
update Delivery_addresses set  phonenumber = phoneCustomer
where address_id = id;
end // 
delimiter ;

delimiter //
create procedure update_status_address(In id int)
begin 
update Delivery_addresses set  address_status = 'DELETE'
where address_id = id;
end // 
delimiter ;


delimiter //
create procedure update_default_address(In id int, str char(15))
begin 
update Delivery_addresses set  address_default = str
where address_id = id;
end // 
delimiter ;

delimiter //
create procedure update_city_address(In id int, city1 char(250))
begin 
update Delivery_addresses set  city = city1
where address_id = id;
end // 
delimiter ;

delimiter //
create procedure update_district_address(In id int, district1 char(250))
begin 
update Delivery_addresses set  district = district1
where address_id = id;
end // 
delimiter ;

delimiter //
create procedure update_address(In id int, address1 char(250))
begin 
update Delivery_addresses set  address = address1
where address_id = id;
end // 
delimiter ;

delimiter //
create procedure search_default_address(In id int)
begin 
select *from Delivery_addresses
where customer_id = id and address_default = 'Mac dinh' and address_status <=> null;
end // 
delimiter ;

delimiter //
create procedure search_address_id(In id int)
begin 
select *from Delivery_addresses
where address_id = id ;
end // 
delimiter ;

delimiter //
create procedure search_address(In id int)
begin 
select *from Delivery_addresses
where customer_id = id and address_status <=> null;
end // 
delimiter ;

delimiter //
create procedure search_book_title(In title1 varchar(100))
begin 
select b.book_id ,p.publishing_company_name ,b.title , b.author ,b.price ,b.detail ,b.quantity ,b.isbn 
	from Books b inner join Publishing_Companies p on b.publishing_company_id = p.publishing_company_id
where title like CONCAT('%', title1, '%') or author like CONCAT('%', title1, '%')
order by b.book_id asc;
end // 
delimiter ;

delimiter //
create procedure search_name_address(In id int)
begin 
select name_customer from Delivery_addresses
where  address_id = id;
end // 
delimiter ;

delimiter //
create procedure detail_order(In id int)
begin 
select o.order_id, o.order_status ,o.date_time ,p.payment_method_name, s.shipping_unit_name ,s.shippingFee, o.address_id
from Orders o inner join Payment_methods p on o.payment_method_id = p.payment_method_id
inner join Shipping_units s on o.shipping_unit_id = s.shipping_unit_id
where  o.order_id = id;
end // 
delimiter ;


delimiter //
create procedure order_exists(In idCustomer int, id int)
begin 
select * from Orders o inner join Delivery_addresses d on o.address_id = d.address_id
where d.customer_id = idCustomer and  o.order_id = id;
end // 
delimiter ;

delimiter //
create procedure display_order(In id int)
begin 
	select o.order_id, o.order_status ,o.date_time ,p.payment_method_name, s.shipping_unit_name ,s.shippingFee, o.address_id
from Orders o inner join Payment_methods p on o.payment_method_id = p.payment_method_id
inner join Shipping_units s on o.shipping_unit_id = s.shipping_unit_id
inner join Delivery_addresses d on o.address_id = d.address_id
where d.customer_id =id
order by o.order_id desc;
end // 
delimiter ;

delimiter //
create procedure search_book_category(In category_i int)
begin 
select b.book_id ,p.publishing_company_name ,b.title , b.author ,b.price ,b.detail ,b.quantity ,b.isbn 
	from Books b inner join Publishing_Companies p on b.publishing_company_id = p.publishing_company_id
    inner join Categories_Books c on b.book_id = c.book_id
    inner join Categories x on c.category_id = x.category_id
where  x.category_id = category_i
order by b.book_id asc;
end // 
delimiter ;

delimiter //
create procedure search_book_category_name(In category_i int, title1 char(100))
begin 
select b.book_id ,p.publishing_company_name ,b.title , b.author ,b.price ,b.detail ,b.quantity ,b.isbn 
	from Books b inner join Publishing_Companies p on b.publishing_company_id = p.publishing_company_id
    inner join Categories_Books c on b.book_id = c.book_id
    inner join Categories x on c.category_id = x.category_id
where  x.category_id = category_i and(b.title like CONCAT('%', title1, '%') or b.author like CONCAT('%', title1, '%'))
order by b.book_id asc;
end // 
delimiter ;

delimiter //
create procedure insert_address(In  customerName varchar(100) , phone char(100) , city1 char(100), district1 char(200), address1 char(250), customer_id int)
begin 
insert into Delivery_addresses(name_customer,phonenumber ,city, district, address, customer_id) values(customerName, phone,  city1, district1, address1, customer_id);
end // 
delimiter ;

delimiter //
create procedure display_address(In idCustomer int)
begin 
select *from Delivery_addresses
where customer_id = idCustomer and address_status <=> null and address_default <=> null;
end // 
delimiter ;

delimiter //
create procedure address_exists(In idCustomer int, id int)
begin 
select *from Delivery_addresses
where customer_id = idCustomer and  address_id = id and address_status <=> null;
end // 
delimiter ;

delimiter //
create procedure update_status_order(In id int, statusOrder char(100))
begin 
update Orders set  order_status = statusOrder
where order_id = id;
end // 
delimiter ;

insert into Publishing_Companies(publishing_company_name) values
('NXB Tong Hop TPHCM'),
('NXB Thanh Nien'),
('NXB Van Hoc'),
('NXB The Gioi'),
('NXB Phu Nu Viet Nam'),
('NXB Tre'),
('NXB Lao Dong'),
('NXB Ha Noi'),
('NXB Cambridge University'),
('NXB Hong Duc'),
('NXB Da Nang'),
('NXB Hoi Nha Van'),
('NXB Dai Hoc Quoc Gia Ha Noi'),
('NXB Tri Thuc'),
('NXB Cong Thuong'),
('NXB Giao Duc Viet Nam'),
('NXB MY Thuat'),
('NXB Dai Hoc Su Pham TPHCM');

insert into Categories(category_name) values
('Van hoc'), ('Kinh Te'), ('Tam Li - Ki Nang Song'), ('Nuoi Day Con'), ('Sach Ngoai Ngu'), ('Sach Giao Khoa - Tham Khao');

insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

                                                                            -- Văn Học-- 
(1, 'Dau Chan Tren Cat', 'Nguyen Phong', '80240', 'Dau chan tren cat la tac pham duoc dich gia Nguyen Phong phong tac ke ve xa hoi Ai Cap the ky thu XIV truoc CN, qua loi ke cua nhan vat chinh - Sinuhe', '12', '5623'),
(2, '999 La Thu Gui Cho Chinh Minh', 'Mieu Cong Tu', '71280', '999 la thu gui cho chinh minh” la mot tac pham dac biet day cam hung den tu tac gia van hoc mang noi tieng Mieu Cong Tu, mang mot mau sac rieng bie', '15', '6357'),
(3, 'Nha Gia Kim (Tai Ban 2017)', 'Paulo Coelho', '55200', 'Tieu thuyet Nha gia kim cua Paulo Coelho nhu mot cau chuyen co tich gian di, nhan ai, giau chat tho, tham dam nhung minh triet huyen bi cua phuong Dong', '13', '5321'),
(4, 'Dieu Dep Nhat Cho Em', 'Mason Deaver', '111200', 'Ben De Backer, 17 tuoi va la mot nonbinary. Sau khi come out voi bo me, Ben bi duoi ra khoi nha giua dem toi voi thoi tiet  1 do C ma khong kip di noi doi giay. Dau don, tuyet vong va suy sup, Ben chi con cac', '13', '6873'),
(3, 'Nha Gia Kim (Tai Ban 2020)', 'Paulo Coelho', '67150', 'Tieu thuyet Nha gia kim cua Paulo Coelho nhu mot cau chuyen co tich gian di, nhan ai, giau chat tho, tham dam nhung minh triet huyen bi cua phuong Dong', '15', '3541'),
(5, 'Van La Mua Ha Nhung Khong Con Chung Ta', 'Hien', '66400', '“Van la mua ha, nhung khong con chung ta”   Cuon sach danh tang rieng cho mua ha vo doi, cho nhung nguoi dang loay hoay tim cho minh khoang troi binh yen de tru ngu tu Hien va Skybooks', '10', '5921'),
(5, 'Vui Ve Khong Quau Nha', 'O Day Zui Ne', '55200', 'Vui ve khong quau nha   xin duoc gui den nhung ban tre dang de gian du, cau co ngoai the gioi kia, nhung ai dang buon phien ve rac roi nao do, “trai tim” nho be nay con phai danh cho niem vui, dung de buc boi', '16', '7941'),
(6, 'Ca Phe Cung Tony (Tai Ban 2017)', 'Tony Buoi Sang', '46800', 'Co doi khi vao nhung thang nam bat dau vao doi, giua vo van nga re va loi khuyen, khi rat nhieu du dinh ma thieu doi phan dinh huong, thi CAM HUNG la dieu quan trong de ban tre bat dau buoc chan ', '17', '8951'),
(2, 'Toi Co Cau Chuyen, Ban Co Ruou Khong?', 'Quan Dong Da Khach', '100620', 'Moi mot thoi khac tren the gian nay deu co mot cau chuyen dang xay ra. Co nhung cau chuyen ban da tung trai qua, ca nhung cau chuyen ma ban chua tung trai qua, the nhung bat ke la chuyen', '12', '4836'),
(7, 'Vo Thuong (Tai Ban 2018)', 'Nguyen Bao Trung', '67150', 'Hy sinh vi nguoi khac luon cho huong thom bay nguoc chieu gio. Ganh nang vi tinh yeu luon song hanh cung suc manh vo song. Bat cu go da nao cham phai tinh yeu deu tro nen bao dung mem mai', '18', '8691');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

(8, 'Cho Hoa Nguyen Soai No', 'Nguyet Ha Tang', '87200', 'Hay tuong tuong ban gap mot nguoi ngoai hanh tinh va anh ay den tu hanh tinh thuc vat. Anh ay the hien tinh cam bang cach ngay ngay gui tang ban mot bong hoa, lai con xin hat giong cua ban de trong nua ', '10', '4189'),
(10, 'Tui Ship Doi Thu X Tui', 'PEPA', '127200', 'Ve Ngon Tu la mot idol hang ba, nhung vi dien xuat kem nen thuong xuyen bi cu dan mang nhao bang. Co Y Luong la doi thu cua Ve Ngon Tu, vua dep trai lai vua co tai nang, hai nguoi thuong xuyen bi dem ra so sanh', '13', '9543'),
(4, 'Nam Nham Mot Ban Tay, Tim Duoc Nguoi Nhu Y', 'Coc Huu Tu', '79200', 'Ngay hop lop nam ay, mot "ke hoach cua trai" tao bao da duoc "hoi chi em" vach san cho co gai hai muoi lam tuoi, ham an, "e ben vung" Tran Hai Nguyet', '16', '2202'),
(10, 'Nhiet Do Xa Giao', 'Carbeeq', '73030', 'Nhiet do xa giao – Cuon sach nhe nhang, diu dang nhung khong kem phan thuc te ma ngoai xa hoi chung ta ai cung tung thay, trai nghiem ve mot cuoc song ninh not, vu loi, co lap,.. dien ra hang ngay', '17', '7924'),
(8, 'Bo Sach Ma Dao To Su - Tap 1&2', 'Mac Huong Dong Khuu', '241380', 'Kiep truoc Nguy Vo Tien bi van nguoi thoa ma, danh tieng xau xa den cung cuc, su de han the ca doi bao ve lai dan nguoi den tieu diet han', '14', '3651'),
(3, 'Ong Gia Va Bien Ca', 'E, Hemingway', '26250', 'Tac pham la ban anh hung ca ca ngoi suc lao dong va khat vong cua con nguoi', '12', '9342'),
(3, 'Hai So Phan - Bia Cung', 'Jeffrey Archer', '117250', '“Hai so phan” khong chi don thuan la mot cuon tieu thuyet, day co the xem la "thanh kinh" cho nhung nguoi doc va suy ngam, nhung ai khong de dai, khong chap nhan loi mon', '14', '3021'),
(3, 'Khong Gia Dinh', 'Hector Malot', '77350', 'Khong Gia Dinh la tieu thuyet noi tieng nhat trong su nghiep van chuong cua Hector Malot', '20', '4076'),
(3, 'Thep Da Toi The Day', 'Mikolai A Ostrovsky', '106080', 'Thep Da Toi The Day giai quyet cho chung ta nhieu van de nhan sinh quan moi, xay dung cho chung ta mot quan niem ve tinh yeu trong sang', '10', '5091'),
(3, 'Bo Gia', 'Mario Puzo', '94500', 'Bo gia la cuon tieu thuyet van hoc hien dai My duoc dong dao ban doc tren the gioi noi chung va tai Viet Nam noi rieng don nhan mot cach "nong hau", va cung khong ngac nhien khi bo phim ra doi an theo kich ban cung duoc chao don nong nhiet khong kem', '16', '4087');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

(12, 'Dieu Ky Dieu Cua Tiem Tap Hoa Namiya ', 'Higashino Keigo', '81375', 'Tai ke chuyen hon nguoi da giup Keigo kheo leo thay doi cac moc dau thoi gian va khong gian, chap noi nhung cau chuyen tuong chung hoan toan rieng re thanh mot ket cau chat che, gay bat ngo tu dau toi cuoi', '12', '2098'),
(6, 'Harry Potter Va Dua Tre Bi Nguyen Rua', 'J.K.Rowling', '108000', 'Tu nhung nhan vat quen thuoc trong bo Harry Potter, kich ban noi ve cuoc phieu luu cua nhung hau due', '18', '3056'),
(3, 'Trai Tre Dac Biet Cua Co Peregrine', 'Ransom Riggs', '90470', 'Jacob lon len cung nhung cau chuyen ong noi thuong hay ke. Cau chuyen ve thuo thieu thoi ong da trai qua tai mot trai tre da duoc phu phep, noi sinh song cua nhung dua tre so huu nang luc phi thuong: mot co be biet bay', '13', '3167'),
(10, 'Ke Nan Sap', 'Minette Walters', '59800', 'Rat nhieu nhan dinh nghe dang ngo va day cam tinh nhu the lai ton tai sat da khap cau chuyen nay, giang len me lo khien khong nhung doc gia ma chinh cac nhan vat cung phai boi roi va lac duong tren hanh trinh di tim that gia', '10', '5102'),
(10, 'Chuyen yeu quai va di trung nhat ban', 'Lafcadio Hearn', '36400', 'O Nhat, kem da, ca vang, chuong gio, phao hoa va le hoi cac xu la nhung thu co tinh dac trung cua mua he, dem mat lanh va tho mong den lam diu khong khi oi nong. Nhung ngoai nhung thu nghe rat tao nha ay ra', '12', '5111');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

                                                                           -- Kinh Tế--

(1, 'Bi Mat Tu Duy Trieu Phu ', 'T Harv Eker', '62560', 'Trong cuon sach nay T. Harv Eker se tiet lo nhung bi mat tai sao mot so nguoi lai dat duoc nhung thanh cong vuot bac', '19', '7111'),
(1, 'Vi Tu Si Ban Chiec Ferrari ', 'Robin Sharma', '57120', 'cuon sach nay la mot trong nhung an pham kinh dien cua the gioi ve de tai truyen cam hung, theo duoi ly tuong song, va huong ve mot cuoc song hanh phuc', '13', '8182'),
(1, 'Vi Giam Doc Mot Phut', 'Ken Blanchard', '32640', 'Trong suot hanh trinh keo dai rong ra nhieu nam, anh da chung kien nhieu cach quan ly khac nhau nhung van chua tim ra nguoi va noi minh mong duoc gap', '14', '9196'),
(6, 'Nha Lanh Dao Khong Chuc Danh', 'Robin Sharma', '57600', 'Robin Sharma da tham lang chia se voi nhung cong ty trong danh sach Fortune 500 va nhieu nguoi sieu giau khac mot cong thuc thanh cong da giup ong tro thanh mot trong nhung nha co van lanh dao duoc theo duoi nhieu nhat the gioi','18', '6789'),
(14, 'Nang Doan Kim Cuong', 'Geshe Michael Roach', '76950', 'Cuon sach nay la mot cau chuyen ve tac gia da xay dung don vi kim cuong lai tai Andin International nhu the nao khi su dung nhung nguyen tac duoc thu thap tu tri tue xua co cua Phat giao', '20', '8981'),
(2, 'Tiktok Marketing', 'Markus Rach', '83400', 'TikTok co the duoc xem la mot hien tuong dot pha trong cac mang xa hoi thoi gian gan day” va mang tinh thu nghiem. Tuy nhien, rat nhieu marketers nhanh nhay da thanh cong ngoai mong doi nho tan dung suc manh cua TikTok', '15', '1915'),
(1, 'Nghe Thuat Ban Hang Bac Cao', 'Zig Ziglar', '114240', 'neu ban mang den cho khach hang nhung gia tri con cao hon gia tri ma le ra ho se nhan duoc thi khong nhung ban da co mot thuong vu thanh cong ma ban con co them mot khach hang san long giup ban co them nhieu khach hang khac nua', '17', '1789'),
(7, 'Tro Thanh Nguoi Ban Hang Xuat Sac ', 'Brian Tracy', '26000', 'Tro Thanh Nguoi Ban Hang Xuat Sac gioi thieu 21 nguyen tac quan trong nhat de ban hang thanh cong do chinh Brian Tracy kham pha va duc ket sau hon 30 nam nghien cuu va dao tao tu duy thanh cong', '12', '4568'),
(7, 'Con Duong Tro Thanh Freelancer Writer', 'Linh Phan', '176000', 'Mo hinh lam viec van phong tu lau da khong con la uu tien hang dau cua the he tre va nhung lua chon kinh doanh linh hoat nhu Nghe viet tu do', '12', '1709'),
(15, 'Trai Nghiem Khach Hang', 'Blake Morgan', '83400', 'Voi tat ca nhung dot pha dang dien ra, lam the nao de mot cong ty co the noi bat? Thuc te cho thay, viec chung ta khien moi nguoi cam thay nhu the nao co tac dong dang ke den nhan thuc cua ho ve chung ta: do chinh la Trai nghiem khach hang', '11', '4050');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

(1, 'Nghi Giau & Lam Giau', 'Napoleon Hill', '74800', 'hon 60 trieu ban duoc phat hanh voi gan tram ngon ngu tren toan the gioi va duoc cong nhan la cuon sach tao ra nhieu trieu phu, mot cuon sach truyen cam hung thanh cong nhieu hon bat cu cuon sach kinh doanh nao trong lich su', '10', '9870'),
(6, 'Tu Tot Den Vi Dai - Jim Collins', 'Jim Collins', '92000', 'Jim Collins cung nhom nghien cuu da miet mai nghien cuu nhung cong ty co buoc nhay vot va ben vung de rut ra nhung ket luan sat suon, nhung yeu to can kip de dua cong ty tu tot den vi dai', '15', '5100'),
(1, 'Khong Bao Gio La That Bai!', 'Chung Ju Yung', '66640', 'Day la an pham dat so luong phat hanh ky luc - 500.000 ban tai Viet Nam. "Khong bao gio la that bai! Tat ca la thu thach" da vuot khoi khuon kho tua de mot cuon sach, tro thanh phuong cham song cua rat nhieu ban tre Viet Nam', '20', '1221'),
(6, 'Neu Toi Biet Duoc Khi Con 20 ', 'Tina Seelig', '51200', 'Ban co 5 do la va 2 gio dong ho de kinh doanh. Ban se lam gi?   Day la mot trong nhung vi du minh hoa duoc nhac den trong cuon sach Neu Toi Biet Duoc Khi Con 20. Tra loi cho cau hoi nay co rat nhieu cach va voi moi cach, ', '15', '5201'),
(15, '7 Phương Pháp Đầu Tư Warren Buffet', 'Mary Buffett', '83400', 'Warren Buffett la chuyen gia dau tu va la mot trong nhung doanh nhan giau co, duoc kinh trong nhat the gioi. Voi 12 nam hoc hoi truc tiep cac chien luoc va thoi quen dau tu cua Warren Buffett, Marry Buffett, cung voi Sean Sear', '10', '4019'),
(7, 'Ai Thuc Su La Nguoi Giau Nhat The Gioi?', 'Song Hong Bing', '90000', 'Phan lon chung ta thuong nghi su hien dien cua dong tien trong cuoc song la mot le duong nhien. Rat it nguoi trong chung ta hieu duoc nguon goc cua tien bac cung nhu su sinh ton va phat trien cua dong tien', '12', '9061'),
(7, 'Su Thong Tri Cua Quyen Luc Tai Chinh', 'Song Hong Bing', '93000', 'cach phan bo tai san hay day mot bo phuong phap doi pho tien te dien hinh. Cuon sach nay nham tra loi nhung cau hoi tu lau da khien chung ta boi roi va chua duoc giai dap: Tai sao tien te lai co xung dot?', '19', '7689'),
(14, 'Su Giau Va Ngheo Cua Cac Dan Toc', 'Davis S Landes', '319200', 'Chinh rac thai va su tan pha nay, da gia tang rat nhieu cung voi san luong va thu nhap, hien de doan khong gian chung ta song va di chuyen.” – Davis S. Landes', '19', '8192'),
(15, 'Dong Tien Gan Lien Loi Nhuan', 'Mike Michalowicz', '119200', 'moi khoi nghiep hoac da khoi nghiep duoc coi la thanh cong (nhung cu lo? Mac du ke hoach kinh doanh thi rat kha thi nhung tham chi doanh thu cang tang, lo cang nhieu', '17', '5673'),
(4, 'Quoc Gia Khoi Nghiep', 'Dan Senor', '131565', '“Quoc gia khoi nghiep” da dem den nhung cai nhin moi me ve con nguoi va dat nuoc Israel, lam sang to phan nao nhung thanh cong tuong chung nhu khong tuong cua dat nuoc nho be nay', '12', '2198');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
(15, 'Nghi Giau Lam Giau 365 Ngay', 'Napoleon Hill', '72090', 'Hay bat dau moi buoi sang voi nguon thuc pham cho suy nghi va hanh dong de nuoi duong co the va tam hon ban, cong viec va ca cac moi quan he cua ban nua. moi thang trong lich “co the – lam” nay co chu de thong nhat cua rieng no', '10', '9103'),
(7, 'Huong Dan Can Ban Ve Cach Kiem Tien', 'Benji Travis', '89400', 'cho den cach chung ta mo rong thi truong voi nhung co hoi moi danh cho nhung ca nhan giau tham vong. Ngay nay tren the gioi, hang nghin nguoi dang kiem duoc nhung thu nhap trieu do tu video truc tuyen, nhung ho lam dieu do bang cach nao?', '15', '6295'),
(6, 'Dau Tu: Ke Hoach 12 Thang', 'Danielle Town', '13600', 'cuon sach nay lam cho the gioi phuc tap cua viec dau tu tro nen don gian, de hieu va de tiep can, se giup ban hinh thanh ke hoach dau tu cua rieng minh va thuc day su tu tin de dua no vao hanh dong', '13', '9561'),
(7, 'Khoi Nghiep 4.0', 'Dorie Clark', '83400', 'Hay lam theo cac huong dan trong sach - tung buoc mot, su dung cac vat dung ban da co san trong tay, ngay truoc mat ban, de tao ra nguon thu nhap doi dao va ben vung', '12', '5391'),
(4, 'Cam Nang Mo Nha Hang', 'Roger Fields', '155220', 'Cam nang mo nha hang nhu mot cuon huong dan khoi nghiep cung cap cho nhung chu nha hang tuong lai cach len ke hoach de mo mot nha hang cua rieng minh, tu kinh phi, xay dung thuc don, marketing quang ba, quan ly tai chinh...', '10', '1093');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

                                                                  -- Tam ly - Ki Nang Song-- 
                                                                  
(3, 'Kheo An Noi Se Co Duoc Thien Ha - Ban Moi', 'Trac Nha', '85800', 'Mot cuon sach hay se tu noi len duoc gia tri cua no, va tu ban than moi doc gia se cam nhan duoc dieu do. Quy doc gia se tu nhien yeu thich mot cuon sach co the cham den trai tim va bo ich cho minh', '10', '7941'),
(2, 'Suc Manh Cua Ngon Tu', 'Shin Dohyeon', '76800', 'Ky nang giao tiep la mot trong nhung ky nang song vo cung quan trong, tuy nhien doi khi chung ta lai khong may de y toi chung. De dat duoc dieu ban mong muon, ngon tu chinh la chiec chia khoa dau tien', '12', '6503'),
(2, 'Hai Huoc Mot Chut The Gioi Se Khac Di', 'The Book Worm', '48600', 'Cuoc doi nay khong the thieu su hai huoc, hai huoc cung la mot net dep, mot gia vi khien cuoc song thu vi hon. Hau nhu khong ai la khong thich hai huoc, di dom, hon nua hai huoc con gan bo mat thiet voi su thanh bai cua cuoc doi moi nguoi', '11', '1682'),
(3, 'Khi Ban Dang Mo', 'Vi Nhan', '89700', 'Nguoi khac co the mang lai cho ban nhieu lam la san khau, con vai dien la do ban dam nhiem. The gioi nay khong doi ban truong thanh, cung chang co ai truong thanh thay ban, ban chi co the tu vuot qua gian kho, tu no luc truong thanh', '18', '7541'),
(7, 'Doc Vi Bat Ky Ai', 'TS David J Lieberman', '63200', 'Hay chiem the thuong phong trong viec chu dong nhan biet dieu can tim kiem - o bat ky ai bang cach “tham nhap vao suy nghi” cua nguoi khac. DOC VI BAT KY AI la cam nang day ban cach tham nhap vao tam tri cua nguoi khac de biet dieu nguoi ta dang nghi', '13', '5601'),
(1, 'Dac Nhan Tam', 'Dale Carnegie', '53200', 'giao tiep voi moi nguoi de dat duoc thanh cong trong cuoc song. Day duoc coi la mot trong nhung cuon sach noi tieng nhat, ban chay nhat va co tam anh huong nhat moi thoi dai ma ban khong nen bo qua', '15', '3041'),
(6, 'Thuat Xu The Cua Nguoi Xua', 'Thu Giang', '32000', 'a hay xet ky mot cach thanh that tam long minh: trong cac ban ma minh thuong, co phai la nhung nguoi thong minh nhat, gan ho, bao gio minh cung thay thap kem hon; hay nhung nguoi that tha nhat, gan ho, bao gio minh cung thay cao trong hon?', '10', '1098'),
(10, '15 Guong Phu Nu', 'Nguyen Hien Le', '59400', 'Ho cung tu tao cho minh cho dung vung vang, su nghiep vung chac trong xa hoi. ho la nhung “sieu nhan” luon phai can bang giua cong viec va cuoc song gia dinh. Ai ma con co tu tuong trong nam khinh nu thi con lac hau, con bao thu phong kien', '10', '9363'),
(6, 'Cai Dung Cua Thanh Nhan', 'Nguyen Duy Can', '32000', 'Cho ma nguoi xua goi la hao kiet at phai co khi tiet hon nguoi. Nhung nhan tinh co cho khong nhin duoc. Boi vay, ke that phu gap nhuc, tuot guom dung day, vuon minh xoc danh. Cai do chua goi la dung', '10', '3665'),
(10, 'Cac Cuoc Doi Ngoai Hang', 'Nguyen Hien Le', '67800', 'khong it hon ma chi co sau? Muon tim ra cau tra loi cach don gian nhat chinh la hay doc cuon sach nay. Ban se biet vi sao chi can doc cau chuyen ve 6 danh nhan nay la ban cung da co the tung buoc hoan thien ban than', '18', '5343');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values

(1, 'Cam Nang Con Trai (Tai Ban 2020)', 'Violeta Basic', '39440', 'Thoi ky truong thanh thuong mang toi nhung thay doi lon. Moi thu bong choc tro nen la ky, than hinh phong phao, vo giong, rau toc moc dai, mui hoi xuat hien... nhung dieu nay doi khi khien cac dang nam nhi thay kho hieu va tham chi lo lang', '11', '4943'),
(1, 'Cam Nang Con Gai (Tai Ban 2019)', 'Violeta Basic', '39440', 'Nhung ai da di qua tuoi day thi co le chang bao gio quen su ngac nhien tot bac khi mot sang dung truoc guong, thay co the minh… khang khac. Va nhung dieu moi me cu “dat diu” nhau den moi ngay', '16', '6503'),
(1, 'Tony Buzan - Sach Day Doc Nhanh', 'Tony Buzan', '161880', 'Tony Buzan cung cap thong tin ve doi mat va bo nao nham giup ban cai thien moi linh vuc lien quan den hoat dong doc hieu cua ban dong thoi chung minh mot dieu: co the doc voi toc do tren 1000 tu moi phut', '13', '5039'),
(1, 'Gio Heo May Da Ve (Tai Ban 2019)', 'BS Do Hong Ngoc', '36000', 'Nhac si Trinh Cong Son cho rang bien gioi giua tuoi chom gia va tuoi tre chi la uoc le. Con BS. Do Hong Ngoc lai cho ta thay mot tuoi chom gia nhieu bien dong va can nhieu dieu chinh de thich nghi', '10', '3553'),
(8, 'Nhung Bai Hoc Tu Bao Ve', 'Tran Si Cu', '79200', 'Chac chan la bo me nao cung hy vong con cai minh lon len that khoe manh va vui ve, tuy nhien, bo me khong the de y, quan tam den con cai moi luc moi noi duoc, cho nen de con hoc cach tu bao ve ban than moi la cach lam toan ven nhat', '20', '6803'),
(4, 'On Gioi, Freud Tra Loi', 'Sarah Tomley', '119200', 'cuon sach cung cap nhieu goc nhin khac nhau ve the gioi tam ly ky la cua con nguoi, nhung diem chung trong nang luc tri tue cua chung ta, cung nhu su doc dao cua tam ly moi nguoi', '15', '2069'),
(5, 'Bi Mat Va Thuc Te Ve Tu Ki Am Thi', 'Emile Coue', '49800', 'neu chung ta kheo leo thuc hien am thi co y thuc, no se giup ta lam chu chinh minh va chua lanh ton thuong, ca the chat lan tinh than. Chung ta cung co the giup do nhung nguoi xung quanh, cung nhau huong toi mot cuoc song an lanh va hanh phuc', '12', '4063'),
(7, 'Kiep Nao Ta Cung Tim Thay Nhau', 'Brian L Weiss', '68850', 'Kiep nao ta cung tim thay nhau noi ve nhung linh hon tri ky, nhung nguoi co moi lien ket vinh vien voi nhau bang tinh yeu thuong, luon gap lai nhau het lan nay den lan khac, qua het kiep nay toi kiep khac', '15', '2854'),
(2, 'Thuat Doc Tam', 'Don Richard Riso', '155220', 'Cuon sach la mot cam nang huong dan chi tiet ve viec su dung tri tue Enneagram cho su phat trien cua tam linh va tam ly', '13', '3041'),
(6, 'Toi Tu Hoc', 'Thu Giang', '48000', 'muc dich cua hoc van doi voi con nguoi dong thoi neu len mot so phuong phap hoc tap dung dan va hieu qua. Tac gia cho rang gia tri cua hoc van nam o su linh hoi va mo mang tri thuc cua con nguoi chu khong don thuan the hien tren bang cap', '20', '8094');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
(1, 'Bi Mat Cua May Man (Kho Nho)', 'Alex Rovira', '24480', 'Cuon sach lam bung sang doc gia tren toan the gioi May man la mot khai niem truu tuong, nhung rat gan gui voi cuoc song va  duoc coi nhu mot tac nhan bi an gop phan vao cac mat hoat dong cua con nguoi', '11', '2033'),
(1, 'Chicken Soup For The Soul 8', 'Mark Victor Hansen', '40800', 'Thong qua quyen sach Chicken Soup for the Golden Soul, chung ta hay cung nhau ton vinh cuoc song. mang den nhung nu cuoi thoai mai va nhom len ngon lua trong tam hon ban. Va mong rang cac ban se truyen nhung cau chuyen nay cho nhieu nguoi khac nua', '12', '2087'),
(1, 'Chicken Soup For The Soul 11', 'Jack Canfield', '40800', 'Vuot qua Thu thach Dau doi duoc trinh bay duoi dang song ngu de giup ban doc co the cam nhan tron ven duoc y nghia cau chuyen bang ca hai ngon ngu', '12', '3081'),
(1, 'Hat Giong Tam Hon', 'First News', '93840', 'Bang mot dinh dang moi me, duoc dau tu cong phu ca ve noi dung lan hinh thuc, nhung nguoi thuc hien cuon sach hy vong day se la mot mon qua de ban trao gui cho nguoi than, ban be', '11', '3850'),
(1, 'Nhung Nguoi Ban Nho', 'Jack Canfield', '40800', 'Chung toi cung mong muon dem lai cho ban mot quan diem moi ve nhung nguoi ban bon chan nay va bat tay vao hanh dong – yeu thuong chung vo dieu kien va tran trong nhung mon qua gian di ma chung mang den cho cuoc song cua ban', '17', '4080');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
                                                                                       -- Nuôi Dạy Con-- 
                                                                                       
(7, 'Nuoi Con Khong Phai La Cuoc Chien', 'Bubu Huong', '79200', 'Cuon sach khong la cam nang de be an nhieu tang can nhanh hay day be nghe loi ram rap, ma giup ban hieu con minh hon. Giup ban hieu chu ky sinh hoc cua con va cach phoi ket hop cuoc song gia dinh voi chu ky sinh hoc do cua be', '15', '4076'),
(7, 'Phuong Phap Day Con Khong Don Roi', 'Daniel J. Siegel', '65400', 'ta cung muon bon tre cam nhan duoc muc do sau sac cua toan bo tinh yeu thuong va thien y cua chung ta, du chung ta dang cong nhan mot hanh dong tu te hay chi ra hanh vi sai trai', '13', '1398'),
(3, 'Cho Den Mau Giao Thi Da Muon', 'Ibuka Masura', '57270', 'cuon sach ban ve phuong phap giao duc tre trong giai doan tu 0 den 3 tuoi cua tac gia Ibuka Masaru, nguoi sang lap tap doan Sony dong thoi la mot nha nghien cuu giao duc', '20', '3965'),
(7, 'Cach Khen, Cach Mang', 'Masami Sasaki', '50150', 'Trong cuon sach nay, ngoai nhung cuoc tro chuyen trao doi kinh nghiem ve cach nuoi day tu bac si Masami Sasaki va co Wakamatsu Aki, chung toi cung them vao mot vai doan gioi thieu khi con nho ho da duoc cha me giao duc con nhu the nao', '12', '2908'),
(7, 'Doc Vi Moi Van De Cua Tre', 'Tracy Hogg', '125550', 'Van de khong la gi khac ngoai mot rac roi can phai giai quyet hoac mot tinh huong doi hoi giai phap sang tao. Hay dat ra dung cau hoi va ban se tim ra cau tra loi chinh xac', '12', '3540'),
(4, 'Con Co The Tu Bao Ve Minh', 'Dagma Geisler', '27360', 'Cuon sach nay duoc tao nen boi nhung hinh anh dang yeu voi cot truyen ro rang,  nhan manh vao thai do CON TON TRONG CO THE CON, CON TON TRONG NGUOI KHAC VA MOI NGUOI CUNG CAN TON TRONG CON', '21', '4180'),
(4, 'Hoc Kieu My Tai Nha', 'Hong Dinh', '97940', 'cuon sach ra doi cung voi tieu chi nhu vay, cho cac gia dinh nhieu niem vui trong viec hoc, bang cac tai nguyen mo mien phi san co tren toan the gioi!', '10', '2051'),
(5, 'Kich Thich Thi Giac ', 'PINGBOOKS', '55900', 'ngay tu khi be moi chao doi, cha me hay trang bi ngay cho con yeu cua minh bo sach KICH THICH THI GIAC BLACK AND WHITE de giup thi giac cua be phat trien hieu qua nhat nhe', '12', '3061'),
(3, 'Bo Sach Ki Nang Song', 'Thien Thai', '51000', 'Cac ban nho than men! Bo me la nguoi yeu thuong ban nhat tren the gian nay day! Neu mot ngay bo me co den don minh muon mot ti thi nhat dinh la da co viec dot xuat lam cham tre chu khong the nao lai quen minh duoc!', '11', '1078'),
(1, 'Bi An Cua Nao Phai', 'GS Makoto Shichida', '66640', 'Cuon sach dem den triet li giao duc lanh manh, nhan van, coi trong va ton vinh moi dua tre', '12', '3096');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
(4, 'De Con Duoc Om', 'BS Tri Doan', '67000', 'De con duoc om co the coi la mot cuon nhat ky hoc lam me thong qua nhung cau chuyen tu trai nghiem thuc te ma chi Uyen Bui da trai qua tu khi mang thai den khi em be chao doi va tro thanh mot co be khoe manh, vui ve', '11', '3095'),
(4, 'An Dam Khong Nuoc Mat', 'Nguyen Thi Ninh', '87150', 'The nao la an dam khong nuoc mat? La khi con khong khoc vi bi ep an va me khong khoc vi con bo bua. La khi con hao hung truoc moi bua an va me hanh phuc thay con an het phan do an me lam', '13', '2018'),
(6, 'An Uong La Hanh Phuc', 'BS Tran Thi Huyen Thao', '75600', 'Ba me da san sang & Buoc dem vung chac vao doi, cuon sach thu ba trong bo BAC SI RIENG CUA BE YEU duoc bac si Huyen Thao chon de tai “an uong”', '12', '2020'),
(10, 'Cho Con Yeu Giac Ngu Ngon', 'Tran Thanh Huyen', '32200', 'de tre co duoc mot giac ngu ngon khong phai la van de don gian. Co nhieu dieu chung ta can tim hieu, co nhieu phuong phap chung ta can hoc hoi trong cuon sach nay de giup tre co giac ngu ngon', '15', '3019'),
(10, 'Cam Nang Cham Soc Tre', 'Huyen Linh', '40800', 'cac ba me tre hoan toan co the tu trang bi cho minh nhung thong tin can thiet, biet cach theo doi va xu ly nhung tinh huong bat thuong, biet cach phong tranh va cham soc tre so sinh mot cach tot nhat', '12', '4018'),
(7, 'Lan Dau Lam Me', 'Masato Takeuchi', '168000', 'Nhung nguoi phu nu lan dau mang thai va sap sua duoc lam me, ban co cam thay hoang mang voi thoi dai nay vi qua nhieu thong tin kien thuc lien quan den thai nhi va kien thuc sinh no', '13', '2805'),
(7, 'Me Nhat Thai Giao', 'Akira Ikegawa', '52000', '“Cam on con vi da chon bo me trong so hang trieu trieu cap doi tren the gioi nay”, “Cam on con da den de giup bo me truong thanh hon.” Do la thong diep ma cuon sach muon gui den voi tat ca cac doc gia', '17', '7485'),
(10, 'Dinh Duong Khoa Hoc', 'Huyen Linh', '30420', 'Cuon sach giai thich tung thoi gian thai nhi phat trien tung tuan nhu the nao va thai phu bien doi ra sao tu hinh dang den cam xuc', '13', '6019'),
(1, 'Con Oi, Ba Me Van Cho', 'BS Le Tieu My', '64000', 'Chuyen co con, dung nhin no nhu nhiem vu neu khong hoan thanh la ban co toi voi troi dat   dieu do vien vong va mo ho lam', '12', '2018'),
(1, 'Thai Ky An Vui', 'Bac si Le Tieu My', '96000', 'Tap sach nho nay se mang den cho cac cap vo chong nhung thong tin co ban nhat ve thai ky va sinh san ma cac to chuc y te Viet Nam cung nhu cac nuoc da nghien cuu, chon loc de pho bien cho nhung nguoi nguoi dan', '15', '6025');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
(2, 'Tuoi Day Thi', 'Nhieu Tac Gia', '53130', 'hay de cuon cam nang “Tuoi day thi   Tu “Cau be” den “Anh chang”” dong hanh cung cac em tren hanh trinh day thi, cung lang nghe nhung loi tu van, chia se de hieu hon ve giai doan vua ly thu lai vua kho nhan nay nhe!', '14', '4013'),
(7, 'Tuoi Day Thi Noi Gi Voi Con?', 'Reiko Uchida', '52000', 'Cuon sach Tuoi day thi noi gi voi con giup cac cha me hieu duoc rang, nuoi con khong chi can tri thuc hay mot phuong phap, ma nuoi con can hon het chinh la tinh yeu thuong vo bo ben cua cha me', '14', '5200'),
(7, 'Doc Vi Tuoi Day Thi Va Hoi Chung Tuoi Teen', 'Lee Jin Ah', '124000', 'Tre khong can nhan den su chi dan tu thay co giao ma tre chi can co nguoi o ben, chia se, dong vien. Chi nhu vay, tre moi chiu mo long minh ra voi cha me minh va se chia nhung lo lang va cang thang tre dang phai chiu dung', '14', '2014'),
(7, 'De Lop 9 Khong La Dang So', 'Nguyen Thanh Hai', '87200', 'tuc la ban dang day con mot thoi quen tot la biet lang nghe nguoi khac. Ngoai ra, day la mot cach rat huu hieu de ban hieu con. Ban hay de y loi con tre, chap noi vao de hieu ve the gioi cua tre tho noi chung va cua con ban noi rieng', '14', '2046'),
(7, 'Chao Tuoi “Dau Rung”', 'Karen Gravelle', '60000', 'Cuon sach nay se giup cac bac phu huynh va con gai ho co the chia se coi mo voi nhau ve chu de kinh nguyet va tinh duc. Co rat nhieu ban nu gap kho khan khi muon thao luan ve nhung chu de nay voi ba me cua minh', '13', '4061');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
(9,'Cambridge Ielts 14 Academic With Answers', 'Cambridge', '126000', 'Cambridge IELTS tu nha xuat ban Cambridge, voi 2 phien ban Academic va General Training da chinh thuc len ke tai Cong ty Co phan Sach Viet Nam', '10', '2405'),
(9, 'Cambridge Ielts 14 General Training With Answers', 'Cambridge', '126000', 'Cambridge IELTS tu nha xuat ban Cambridge, voi 2 phien ban Academic va General Training da chinh thuc len ke tai Cong ty Co phan Sach Viet Nam', '14', '2003'),
(11,'Giai Thich Ngu Phap Tieng Anh', 'Mai Lan Huong', '144000', 'Sach Giai Thich Ngu Phap Tieng Anh Voi Bai Tap Va Dap An duoc bien soan thanh 9 chuong, de cap den nhung van de ngu phap tu co ban den nang cao.', '11','2000'),
(11, 'Ngu Phap Tieng Anh', 'Mai Lan Huong', '62400', 'Ngu phap tieng anh tong hop cac chu diem ma cac em hoc sinh can nam vung', '12', '2001'),
(10, 'Ngu Phap Va Giai Thich', 'Vu Thi Mai Phuong', '13000', 'Viec tiep nhan, trau doi, ren luyen ngu phap tieng Anh can cho moi doi tuong muon hoc ngon ngu nay.', '13', '2002');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- SÁCH HỌC NGOẠI NGỮ - TIẾNG NHẬT

(6, 'N1 - 3000 Tu Vung Can Thiet', 'Arc Academy', '90000', 'Bo sach hoc tu vung cap do N5 ~ N1 danh cho Ky thi Nang luc Nhat ngu.', '21', '1001'),
(6, 'N2 - 2500 Tu Vung Can Thiet', 'Arc Academy', '82800', 'Bo sach hoc tu vung cap do N5 ~ N1 danh cho Ky thi Nang luc Nhat ngu.', '22', '1002'),
(6, 'N3 - 2000 Tu Vung Can Thiet', 'Arc Academy', '79200', 'Bo sach hoc tu vung cap do N5 ~ N1 danh cho Ky thi Nang luc Nhat ngu.', '23', '1003'),
(6, 'N4 - 1500 Tu Vung Can Thiet', 'Arc Academy', '56160', 'Bo sach hoc tu vung cap do N5 ~ N1 danh cho Ky thi Nang luc Nhat ngu.', '24', '1004'),
(6, 'N5 - 1000 Tu Vung Can Thiet', 'Arc Academy', '51840', 'Bo sach hoc tu vung cap do N5 ~ N1 danh cho Ky thi Nang luc Nhat ngu.', '25', '1005');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- SÁCH HỌC NGOẠI NGỮ - TIẾNG HOA

(10, 'Giao Trinh Han Ngu 1', 'Nhieu Tac Gia', '57850', 'Giao trinh Han ngu tap 1 phien ban moi tai app – Cuon giao trinh Han ngu phien ban moi duy nhat tai Viet Nam', '30', '1006'),
(10, 'Giao Trinh Han Ngu 2', 'Duong Ky Chau', '74750', 'Bo Giao trinh Han ngu moi nay la phien ban da duoc chinh sua moi nhat, duoc ket hop hoc cung ung dung tien loi cua MCBooks.', '31', '1007'),
(10, 'Giao Trinh Han Ngu Boya', 'MCBOOKS', '64350', 'Cuon sach tieng Trung thuoc trinh do so cap giup nguoi hoc tang von tu vung va ngu phap mot cach nhanh chong.', '32', '1008'),
(13, 'Giao Trinh Han Ngu - Tap 2', 'Hanyu Jiaocheng', '61750', 'Voi bat cu mot hoc vien tieng Trung nao, cuon sach giao trinh Han ngu la khong the thieu duoc.', '33', '1009'),
(13, 'Giao Trinh Han Ngu - Tap 2', 'Duong Ky Chau', '61750', 'Voi bat cu mot hoc vien tieng Trung nao, cuon sach giao trinh Han ngu la khong the thieu duoc.', '34', '1010');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- SÁCH HỌC NGOẠI NGỮ - TIẾNG HÀN

(13, 'Tieng Han Tong Hop Danh Cho Nguoi Viet', 'Cho Hang Rok', '45500', 'Neu ban da co trong tay cuon Giao trinh tieng Han tong hop danh cho nguoi Viet Nam – so cap 1 thi ngay lap tuc phai so huu them cuon sach bai tap nay.', '35', '1011'),
(13, 'Tieng Han Tong Hop Danh Cho Nguoi Viet', 'Cho Hang Rok', '45500', 'Neu ban da co trong tay cuon Giao trinh tieng Han tong hop danh cho nguoi Viet Nam – so cap 2 thi ngay lap tuc phai so huu them cuon sach bai tap nay.', '36', '1012'),
(10, 'Ngu Phap Tieng Han Thong Dung', 'Min Jin Young', '130000', 'La cuon sach thu hai trong bo sach Ngu phap tieng Han Quoc', '37', '1013'),
(10, 'Ngu Phap Tieng Han Thong Dung', 'Nhieu tac gia', '185250', 'Nhieu nam gan day, su quan tam den Han Quoc va nhu cau kham pha van hoa Han Quoc ngay cang tang len dan den day manh nhu cau hoc tieng Han cua nhieu nguoi.', '38', '1014'),
(10, 'Lam Chu Ngu Phap Tieng Han', 'Nhieu tac gia', '182000', 'Voi bat ky ngon ngu nao, muc tieu cuoi cung cua nguoi hoc cung la de su dung ngon ngu do thanh thao trong giao tiep nhu tieng me de.', '39', '1015');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- SÁCH HỌC NGOẠI NGỮ - TIẾNG PHÁP

(13, 'Cach Chia Dong Tu Trong Tieng Phap', 'TS Nguyen Thuc', '28600', 'Cach Chia Dong Tu Trong Tieng Phap', '40', '1016'),
(10, 'Tieng Phap Toan Tap ', 'David M Stillman', '74100', '"Tieng Phap Toan Tap" la mot tap sach danh cho cac hoc vien tieng Phap o trinh do trung cap va cao cap nhu mot cong cu huu dung trong viec on tap va phat trien ngon ngu.', '41', '1017'),
(10, 'Chu Diem Van Pham Tieng Phap', 'Nguyen Thuan Hau', '63990', 'Cai kho nhat cua tieng Phap la van pham. Du hien nay tieng Phap cai tien nhieu nhung van con kho. Do la uu diem dac thu cua tieng Phap. Nhung khong phai khong hoc hay khong the hoc duoc.', '42', '1018'),
(6, '734 Chuyen Muc Ve Ngu Phap', 'AHamon', '85000', '734 Chuyen Muc Ve Ngu Phap Tieng Phap Cho Moi Nguoi', '43', '1019'),
(1, '450 Nouveaux Exercices', 'Odile Grand', '46400', '450 Nouveaux Exercices - Conjugaison Niveau Intermediare', '44', '1020');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- GIÁO KHOA - THAM KHẢO - CẤP 3

(16, 'Sach Giao Khoa Bo Lop 10 Co Ban', 'Bo Giao Duc Va Dao Tao', '155800', 'Sach Giao Khoa Bo Lop 10 Co Ban - Sach Bai Hoc (Bo 14 Cuon) (2020)', '14', '1021'),
(16, 'Sach Giao Khoa Bo Lop 11 Co Ban', 'Bo Giao Duc Va Dao Tao', '160550', 'Sach Giao Khoa Bo Lop 11 Co Ban - Sach Bai Hoc (Bo 14 Cuon) (2020)', '15', '1022'),
(16, 'Sach Giao Khoa Bo Lop 12 Co Ban', 'Bo Giao Duc Va Dao Tao', '171000', 'Sach Giao Khoa Bo Lop 12 Co Ban - Sach Bai Hoc (Bo 14 Cuon) (2020)', '16', '1023'),
(16, 'Atlat Dia Li Viet Nam - 2020', 'Bo Giao Duc Va Dao Tao', '27900', 'Atlat Dia Li Viet Nam - 2020', '17', '1024'),
(16, 'Dai So 10 - Nang Cao (2020)', 'Bo Giao Duc Va Dao Tao', '12600', 'Dai So 10 - Nang Cao (2020)', '18', '1025');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- GIÁO KHOA - THAM KHẢO - CẤP 2

(16, 'Sach Giao Khoa Bo Lop 6', 'Bo Giao Duc Va Dao Tao', '109250', 'Sach Giao Khoa Bo Lop 6 - Sach Bai Hoc Phia Nam (Bo 12 Cuon) (2020)', '19', '1026'),
(16, 'Sach Giao Khoa Bo Lop 7', 'Bo Giao Duc Va Dao Tao', '127300', 'Sach Giao Khoa Bo Lop 7 - Sach Bai Hoc Phia Nam (Bo 12 Cuon) (2020)', '20', '1027'),
(16, 'Sach Giao Khoa Bo Lop 8', 'Bo Giao Duc Va Dao Tao', '141550', 'Sach Giao Khoa Bo Lop 8 - Sach Bai Hoc Phia Nam (Bo 13 Cuon) (2020)', '19', '1028'),
(16, 'Sach Giao Khoa Bo Lop 9', 'Bo Giao Duc Va Dao Tao', '129200', 'Sach Giao Khoa Bo Lop 9 - Sach Bai Hoc Phia Nam (Bo 12 Cuon) (2020)', '20', '1029'),
(16, 'Tap Ban Do Dia Li 7 (2020)', 'Bo Giao Duc Va Dao Tao', '26100', 'Tap Ban Do Dia Li 7 (2020)', '26', '1030');
insert into Books(publishing_company_id, title, author, price, detail, quantity, isbn) values
-- GIÁO KHOA - THAM KHẢO - CẤP 1

(18, 'Tieng Viet 1 - Tap 1 (Bo Sach Canh Dieu)', 'Nguyen Minh Thuyet', '32300', 'Sach day hoc sinh lop 1 hoc doc, hoc viet va phat trien cac ki nang nghe, noi tieng Viet.', '47', '1036'),
(16, 'Sach Giao Khoa Bo Lop 2 (Bo 13 Cuon) (2020)', 'Bo Giao Duc Va Dao Tao', '95760', 'Sach Giao Khoa Bo Lop 2 (Bo 13 Cuon) (2020)', '48', '1037'),


-- GIÁO KHOA - THAM KHẢO - MẪU GIÁO

(17, 'Tu Sach Cho Be Vao Lop 1', 'Le Tue Minh, Le Thu Ngoc', '6400', 'Con ban sap vao lop 1? Khong chi be hoi hop ma chinh ban – nhung bac lam cha me cung co tam trang do.', '27', '1031'),
(17, 'Tu Sach Cho Be Vao Lop 1', 'Le Tue Minh, Le Thu Ngoc', '6320', 'Con ban sap vao lop 1? Khong chi be hoi hop ma chinh ban – nhung bac lam cha me cung co tam trang do.', '28', '1032'),
(17, 'Tu Sach Be Vao Lop Mot', 'Le Tue Minh, Le Thu Ngoc', '8800', 'Tu Sach Be Vao Lop Mot - Vo Tap Viet Chu Cai Viet Thuong - Tap 2 (Tai Ban 2019)', '29', '1033'),
(17, 'Tu Sach Cho Be Vao Lop 1', 'Le Tue Minh, Le Thu Ngoc', '6320', 'Con ban sap vao lop 1? Khong chi be hoi hop ma chinh ban – nhung bac lam cha me cung co tam trang do.', '45', '1034'),
(17, 'Tu Sach Be Vao Lop 1', 'Le Tue Minh, Le Thu Ngoc', '8690', 'Be Tap To Net Co Ban - Danh Cho Be 4 5 Tuoi', '46', '1035');

insert into Categories_Books(book_id, category_id) values
(1,1), (2,1), (3,1), (4,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10,1), (11,1), (12,1), (13,1), (14,1), (15,1), (16,1), (17,1), (18,1), (19,1), (20,1), (21,1), (21,1), (22,1), (23,1), (24,1), (25,1),
(26,2), (27,2), (28,2), (29,2), (30,2), (31,2), (32,2), (33,2), (34,2), (35,2), (36,2), (37,2), (38,2), (39,2), (40,2), (41,2), (42,2), (43,2), (44,2), (45,2), (46,2), (47,2), (48,2), (49,2), (50,2),
(51,3), (52,3), (53,3), (54,3), (55,3), (56,3), (57,3), (58,3), (59,3), (60,3), (61,3), (62,3), (63,3), (64,3), (65,3), (66,3), (67,3), (68,3), (69,3), (70,3), (71,3), (72,3), (73,3), (74,3), (75,3),
(76,4), (77,4), (78,4), (79,4), (80,4), (81,4), (82,4), (83,4), (84,4), (85,4), (86,4), (87,4), (88,4), (89,4), (90,4), (91,4), (92,4), (93,4), (94,4), (95,4), (96,4), (97,4), (98,4), (99,4), (100,4),
(101,5), (102,5), (103,5), (104,5), (105,5), (106,5), (107,5), (108,5), (109,5), (110,5), (111,5), (112,5), (113,5), (114,5), (115,5), (116,5), (117,5), (118,5), (119,5), (120,5), (121,5), (122,5), (123,5), (124,5), (125,5),
(126,6), (127,6), (128,6), (129,6), (130,6), (131,6), (132,6), (133,6), (134,6), (135,6), (136,6), (137,6), (138,6), (139,6), (140,6), (141,6), (142, 6);

insert into Customers(customer_name ,	phonenumber ,gender,birth_date ,email , password_customer) values
('Tuyet Tuyet', '0922344554', 'nu', '2001-21-02', 'anhtuyet@gmail.com','Tuyet2001');