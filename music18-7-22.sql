USE [MusicStore]
GO
ALTER TABLE [VerificationToken] DROP CONSTRAINT [FK_USER_VERIFY_TOKEN]
GO
ALTER TABLE [subtitle] DROP CONSTRAINT [FK__subtitle__song_i__45F365D3]
GO
ALTER TABLE [song] DROP CONSTRAINT [FK__song__genre_id__44FF419A]
GO
ALTER TABLE [song] DROP CONSTRAINT [FK__song__artist_id__440B1D61]
GO
ALTER TABLE [song] DROP CONSTRAINT [FK__song__album_id__4316F928]
GO
ALTER TABLE [PasswordResetToken] DROP CONSTRAINT [FK_USER_PASSWORD_TOKEN]
GO
ALTER TABLE [album] DROP CONSTRAINT [FK__album__artist_id__4222D4EF]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VerificationToken]') AND type in (N'U'))
DROP TABLE [VerificationToken]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[subtitle]') AND type in (N'U'))
DROP TABLE [subtitle]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[song]') AND type in (N'U'))
DROP TABLE [song]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[PasswordResetToken]') AND type in (N'U'))
DROP TABLE [PasswordResetToken]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[genre]') AND type in (N'U'))
DROP TABLE [genre]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[artist]') AND type in (N'U'))
DROP TABLE [artist]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[album]') AND type in (N'U'))
DROP TABLE [album]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[accounts]') AND type in (N'U'))
DROP TABLE [accounts]
GO
USE [master]
GO
DROP DATABASE [MusicStore]
GO
CREATE DATABASE [MusicStore]
GO
USE [MusicStore]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [accounts](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[first_name] [varchar](50) NULL,
	[last_name] [varchar](50) NULL,
	[email] [varchar](200) NULL,
	[password] [varchar](128) NULL,
	[role] [varchar](50) NULL,
	[enabled] [bit] NULL,
	[is_edited] [bit] NULL,
	[username] [varchar](50) NULL,
	[fullname] [varchar](50) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [album](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[artist_id] [int] NULL,
	[release_date] [date] NULL,
	[image] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [artist](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[description] [varchar](50) NULL,
	[image] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [genre](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [PasswordResetToken](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[token] [varchar](255) NULL,
	[expiration_time] [date] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_PasswordResetToken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [song](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[media] [varchar](500) NULL,
	[album_id] [int] NULL,
	[artist_id] [int] NULL,
	[lyric] [ntext] NULL,
	[genre_id] [int] NULL,
	[view] [int] NULL,
	[video] [varchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [subtitle](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[song_id] [int] NULL,
	[vtt] [varchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[news](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](50) NULL,
	[description] [nvarchar](50) NULL,
	[image] [nvarchar](50) NULL,
	[view] [int] NULL,
	[created_at] [nvarchar](50) NULL,
 CONSTRAINT [PK_news] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [VerificationToken](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[token] [varchar](255) NULL,
	[expiration_time] [date] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_VerificationToken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [accounts] ON 

INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname]) VALUES (1, N'Luu', N'Thanh', N'sluuthanh@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_MODERATOR', 1, 1, N'mod', N'Sang')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname]) VALUES (2, N'Luu', N'Thanh', N'sluuthanh1@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_ADMIN', 1, 1, N'admin', N'Sang')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname]) VALUES (3, N'Luu', N'Thanh', N'sluuthanh2@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_EDITOR', 1, 1, N'editor', N'Sang')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname]) VALUES (4, N'Luu', N'Thanh', N'sluuthanh3@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_USER', 1, 1, N'user', N'Sang')
SET IDENTITY_INSERT [accounts] OFF
GO
SET IDENTITY_INSERT [album] ON 

INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (1, N'Sang1', 1, CAST(N'2019-05-26' AS Date), N'bird.jpg')
SET IDENTITY_INSERT [album] OFF
GO
SET IDENTITY_INSERT [artist] ON 

INSERT [artist] ([id], [name], [description], [image]) VALUES (1, N'son tung', N'huhu', N'cat.jpg')
SET IDENTITY_INSERT [artist] OFF
GO
SET IDENTITY_INSERT [genre] ON 

INSERT [genre] ([id], [name]) VALUES (1, N'Pop')
INSERT [genre] ([id], [name]) VALUES (2, N'Jazz')
SET IDENTITY_INSERT [genre] OFF
GO
ALTER TABLE [album]  WITH CHECK ADD FOREIGN KEY([artist_id])
REFERENCES [artist] ([id])
GO
ALTER TABLE [PasswordResetToken]  WITH CHECK ADD  CONSTRAINT [FK_USER_PASSWORD_TOKEN] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [PasswordResetToken] CHECK CONSTRAINT [FK_USER_PASSWORD_TOKEN]
GO
ALTER TABLE [song]  WITH CHECK ADD FOREIGN KEY([album_id])
REFERENCES [album] ([id])
GO
ALTER TABLE [song]  WITH CHECK ADD FOREIGN KEY([artist_id])
REFERENCES [artist] ([id])
GO
ALTER TABLE [song]  WITH CHECK ADD FOREIGN KEY([genre_id])
REFERENCES [genre] ([id])
GO
ALTER TABLE [subtitle]  WITH CHECK ADD FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [VerificationToken]  WITH CHECK ADD  CONSTRAINT [FK_USER_VERIFY_TOKEN] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [VerificationToken] CHECK CONSTRAINT [FK_USER_VERIFY_TOKEN]
GO
USE [master]
GO
ALTER DATABASE [MusicStore] SET  READ_WRITE 
GO
