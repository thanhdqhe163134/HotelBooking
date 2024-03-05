CREATE DATABASE HotelBooking;
GO
USE HotelBooking;
GO

CREATE TABLE Room (
    RoomID INT IDENTITY(1,1) PRIMARY KEY,
    RoomType NVARCHAR(50),
    Price DECIMAL(10, 2),
    Status NVARCHAR(20),
	Description NVARCHAR(MAX),
	IMG NVARCHAR(255),
    CreatedDate DATETIME,
	CreatedBy NVARCHAR(100),
	UpdatedDate DATETIME,
	UpdatedBy NVARCHAR(100),
	IsDelete bit DEFAULT 0,
	DeletedDate DATETIME,
	DeletedBy NVARCHAR(100)
);

CREATE TABLE Customer (
    CustomerID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100),
    Email NVARCHAR(100),
    Phone NVARCHAR(15),
    PersonalInfo NVARCHAR(MAX)
);

CREATE TABLE Account (
	AccountID INT IDENTITY(1,1) PRIMARY KEY,
	Username NVARCHAR(20),
	Password NVARCHAR(max),
	CustomerID INT,
	Role NVARCHAR(10),
	CreatedDate DATETIME,
	CreatedBy NVARCHAR(100),
	UpdatedDate DATETIME,
	UpdatedBy NVARCHAR(100),
	IsDelete bit DEFAULT 0,
	DeletedDate DATETIME,
	DeletedBy NVARCHAR(100),
	FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
)

CREATE TABLE Invoice (
    InvoiceID INT IDENTITY(1,1) PRIMARY KEY,
    CustomerID INT,
    RoomID INT,
    CheckInDate DATETIME,
    CheckOutDate DATETIME,
    Total DECIMAL(10, 2),
    PaymentMethod NVARCHAR(20),
	Note NVARCHAR(MAX),
	CreatedDate DATETIME,
	CreatedBy NVARCHAR(100),
	UpdatedDate DATETIME,
	UpdatedBy NVARCHAR(100),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

CREATE TABLE Service (
    ServiceID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100),
    Price DECIMAL(10, 2),
	CreatedDate DATETIME,
	CreatedBy NVARCHAR(100),
	UpdatedDate DATETIME,
	UpdatedBy NVARCHAR(100)
);

CREATE TABLE InvoiceService (
    InvoiceID INT,
    ServiceID INT,
	CreatedDate DATETIME,
	CreatedBy NVARCHAR(100),
	UpdatedDate DATETIME,
	UpdatedBy NVARCHAR(100),
    FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID),
    FOREIGN KEY (ServiceID) REFERENCES Service(ServiceID)
);

INSERT INTO Room (RoomType, Price, Status, Description, IMG, CreatedDate, CreatedBy)
VALUES ('Standard', 100.00, 'Available','Air conditioner, Fan, TV, Water heater, Wifi, Fridge, The simplest room with minimal equipment, Small area, Low floor, No view','images/room1.jpg', GETDATE(), 'admin'),
       ('Normal', 200.00, 'Available','Air conditioner, Fan, TV, Water heater, Wifi, Fridge, The simplest room with minimal equipment, Small area, Low floor, No view','images/room2.jpg', GETDATE(), 'admin');

INSERT INTO Customer (Name, Email, Phone, PersonalInfo)
VALUES ('Customer 1', 'customer1@email.com', '1234567890', 'Personal info for Customer 1'),
       ('Customer 2', 'customer2@email.com', '0987654321', 'Personal info for Customer 2'); 

INSERT INTO Account (Username, Password, CustomerID, Role, CreatedDate, CreatedBy)
VALUES ('admin', '123456', 1, 'admin', GETDATE(), 'admin'),
       ('user', '123456', 2, 'user', GETDATE(), 'admin');



INSERT INTO Service (Name, Price, CreatedDate, CreatedBy)
VALUES ('Snack', 50.00, GETDATE(), 'admin'),
       ('Noodle', 75.00, GETDATE(), 'admin');



