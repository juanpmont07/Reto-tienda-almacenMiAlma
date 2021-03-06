USE master ;  
GO  
Create database almacenMiAlma;
go
use almacenMiAlma;
go
Create table cliente(
cedula varchar(15)  primary key,
nombre varchar(50)
);
go
Create table producto(
id int primary key identity(1,1),
nombre varchar(100),
descripcion varchar(200),
imagen varchar(400),
precio float
);
create table listaPrecio(
 idProducto int primary key,
 valor float
)
go
Create table pedido(
id int identity(1,1),
idProducto int, 
idCliente varchar(15),
fechaCompra date,
detalle varchar(200),
FOREIGN KEY (idProducto) REFERENCES producto(id),
FOREIGN KEY (idCliente) REFERENCES cliente(cedula),
);
go
CREATE PROCEDURE registrarCliente
@cedula VARCHAR(15), 
@nombre VARCHAR(50)
AS 
INSERT INTO cliente (cedula,nombre)
VALUES (@cedula, @nombre)
GO
/* editar cliente*/
CREATE PROCEDURE editarCliente 
@cedula VARCHAR(15),
@nombre VARCHAR(50)
AS 
update cliente set nombre = @nombre
where cedula = @cedula
GO

/*eliminar cliente*/
CREATE PROCEDURE eliminarcliente 
@cedula VARCHAR(15)
AS 
delete cliente where cedula = @cedula
GO

/* crear pedido */
CREATE PROCEDURE registrarPedido
@idProducto int, 
@idCliente VARCHAR(15),
@fechaCompra date,
@detalle VARCHAR(200)
AS 
INSERT INTO pedido (idProducto,idCliente,fechaCompra,detalle)
VALUES (@idProducto, @idCliente, @fechaCompra, @detalle)
GO

/*Editar pedido*/
CREATE PROCEDURE editarPedido
@id INT,
@idProducto int, 
@idCliente VARCHAR(15),
@fechaCompra DATE ,
@detalle VARCHAR(200)
AS 
update pedido set fechaCompra=@fechaCompra,detalle=@detalle
 where id=@id
GO

/*Eliminar pedido*/
CREATE PROCEDURE eliminarPedido
@id int
AS 
delete pedido where id=@id
GO

/*crear producto*/
CREATE PROCEDURE registrarProducto
@nombre varchar(100),
@descripcion varchar(200),
@imagen varchar(400),
@precio float
AS
INSERT INTO producto (nombre,descripcion,imagen,precio)
VALUES (@nombre, @descripcion,@imagen,@precio)
GO
/*editar producto*/
CREATE PROCEDURE editarproducto 
@id int,
@nombre varchar(100),
@descripcion varchar(200),
@imagen varchar(400),
@precio float
AS 
update producto set nombre = @nombre, descripcion = @descripcion, imagen=@imagen, precio=@precio
where id = @id
GO
/*eliminar producto*/
CREATE PROCEDURE eliminarproducto 
@id int
AS 
delete producto
where id = @id
GO
/*consultar pedido por fecha*/
create procedure consultarPedidoPorFecha
@fechaInicial varchar(12),
@fechaFinal varchar(12)
as
 select p.nombre,p.imagen, c.cedula,c.nombre, pe.fechaCompra  as reporte from pedido pe inner join cliente c on c.cedula = pe.idCliente
 inner join producto p on pe.idProducto= p.id
 where fechaCompra>= @fechaInicial and fechaCompra<=@fechaFinal
GO


/*consultar pedido por id de producto*/
create procedure consultarPedidoPorIdProducto
@id int
as
  select * from pedido pe inner join  producto p on p.id = pe.idProducto
 inner join cliente c on c.cedula = idCliente
 where p.id = @id
GO
/*consultar pedido por id de cliente*/
create procedure consultarPedidoPorIdCliente
@id varchar(15)
as
  select * from pedido pe inner join  producto p on p.id = pe.idProducto
 inner join cliente c on c.cedula = idCliente
 where c.cedula = @id
go