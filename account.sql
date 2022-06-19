USE [MusicStore]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 6/19/2022 8:06:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounts](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](128) NULL,
	[fullname] [varchar](50) NULL,
	[role] [varchar](50) NULL,
 CONSTRAINT [PK_accounts] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO [dbo].[accounts]
           ([username]
           ,[password]
           ,[fullname]
           ,[role])
     VALUES
           ('admin','$2a$10$hoxnYbp.BPyfpX2vFTH9XutFUwfBtTF5m3GvJxwhvbQ3udAhGVCjm','Sang','ROLE_ADMIN')
GO

GO
INSERT INTO [dbo].[accounts]
           ([username]
           ,[password]
           ,[fullname]
           ,[role])
     VALUES
           ('user','$2a$10$hoxnYbp.BPyfpX2vFTH9XutFUwfBtTF5m3GvJxwhvbQ3udAhGVCjm','Sang','ROLE_USER')
GO

