USE [master]
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
CREATE TABLE [brand](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](200) NULL,
 CONSTRAINT [PK_brand] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [comment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](4000) NULL,
	[account_id] [bigint] NULL,
	[song_id] [int] NULL,
 CONSTRAINT [PK_comment] PRIMARY KEY CLUSTERED 
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
CREATE TABLE [playlist](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[account_id] [bigint] NULL,
 CONSTRAINT [PK_playlist] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [playlistitem](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[song_id] [int] NULL,
	[playlist_id] [int] NULL,
 CONSTRAINT [PK_playlistitem] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[price] [int] NULL,
	[image] [varchar](200) NULL,
	[quantity] [int] NULL,
	[description] [varchar](500) NULL,
	[brand_id] [int] NULL,
	[category_id] [int] NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
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
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (2, N'hello ', 4, CAST(N'2022-07-15' AS Date), N'nct.jpg')
SET IDENTITY_INSERT [album] OFF
GO
SET IDENTITY_INSERT [artist] ON 

INSERT [artist] ([id], [name], [description], [image]) VALUES (1, N'son tung', N'huhu', N'cat.jpg')
INSERT [artist] ([id], [name], [description], [image]) VALUES (2, N'abc', N'', N'department7.jpg')
INSERT [artist] ([id], [name], [description], [image]) VALUES (3, N'hoang pro 2', N'', N'faculty1.jpg')
INSERT [artist] ([id], [name], [description], [image]) VALUES (4, N'NCT Dream', N'', N'nct.jpg')
SET IDENTITY_INSERT [artist] OFF
GO
SET IDENTITY_INSERT [brand] ON 

INSERT [brand] ([id], [name]) VALUES (1, N'Exoo')
INSERT [brand] ([id], [name]) VALUES (2, N'NMIX')
SET IDENTITY_INSERT [brand] OFF
GO
SET IDENTITY_INSERT [category] ON 

INSERT [category] ([id], [name]) VALUES (1, N'FanLight')
INSERT [category] ([id], [name]) VALUES (2, N'Trong')
SET IDENTITY_INSERT [category] OFF
GO
SET IDENTITY_INSERT [genre] ON 

INSERT [genre] ([id], [name]) VALUES (1, N'Pop')
INSERT [genre] ([id], [name]) VALUES (2, N'Jazz')
SET IDENTITY_INSERT [genre] OFF
GO
SET IDENTITY_INSERT [product] ON 

INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (1, N'hoang', 200, N'02-1024x1024-min.jpg', NULL, N'', 1, 1)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (2, N'FanLight Exo', 2000, N'02-1024x1024-min.jpg', 8888888, N'sad', 1, 1)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (3, N'duc nam idol', 1300, N'download - Copy.jpg', 6, N'', 1, 1)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (4, N'fl', 143, N'02-1024x1024-min.jpg', 2, N'dsa', 1, 1)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (5, N'hoang', 200, N'02-1024x1024-min.jpg', 200, N'edk', 1, 1)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (6, N'duc nam idol', 200, N'download.jpg', 12, N'sad', 2, 2)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (7, N'iphone', 3200, N'02-1024x1024-min.jpg', 200, N'asd', 1, 1)
INSERT [product] ([id], [name], [price], [image], [quantity], [description], [brand_id], [category_id]) VALUES (8, N'Exo Fanlight', 200, N'Facilities2.jpg', 12, N'sad', 1, 1)
SET IDENTITY_INSERT [product] OFF
GO
SET IDENTITY_INSERT [song] ON 

INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video]) VALUES (1, N'Dive Into You', N'diveintoyou.mp4', 2, 4, N'', 1, 6, N'diveintoyou.mp4')
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video]) VALUES (2, N'Rainbow', N'NCT DREAM - ''Rainbow'' - Line Distribution.mp4', 2, 4, N'', 1, 3, N'rainbow.mp4')
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video]) VALUES (3, N'Chewing Gum', N'chewinggum.mp4', 2, 4, N'', 1, 5, N'chewinggum.mp4')
SET IDENTITY_INSERT [song] OFF
GO
ALTER TABLE [album]  WITH CHECK ADD FOREIGN KEY([artist_id])
REFERENCES [artist] ([id])
GO
ALTER TABLE [comment]  WITH CHECK ADD  CONSTRAINT [FK_comment_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [comment] CHECK CONSTRAINT [FK_comment_accounts]
GO
ALTER TABLE [comment]  WITH CHECK ADD  CONSTRAINT [FK_comment_song] FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [comment] CHECK CONSTRAINT [FK_comment_song]
GO
ALTER TABLE [PasswordResetToken]  WITH CHECK ADD  CONSTRAINT [FK_USER_PASSWORD_TOKEN] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [PasswordResetToken] CHECK CONSTRAINT [FK_USER_PASSWORD_TOKEN]
GO
ALTER TABLE [playlist]  WITH CHECK ADD  CONSTRAINT [FK_playlist_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [playlist] CHECK CONSTRAINT [FK_playlist_accounts]
GO
ALTER TABLE [playlistitem]  WITH CHECK ADD  CONSTRAINT [FK_playlistitem_playlist] FOREIGN KEY([playlist_id])
REFERENCES [playlist] ([id])
GO
ALTER TABLE [playlistitem] CHECK CONSTRAINT [FK_playlistitem_playlist]
GO
ALTER TABLE [playlistitem]  WITH CHECK ADD  CONSTRAINT [FK_playlistitem_song] FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [playlistitem] CHECK CONSTRAINT [FK_playlistitem_song]
GO
ALTER TABLE [product]  WITH CHECK ADD  CONSTRAINT [FK_product_brand] FOREIGN KEY([brand_id])
REFERENCES [brand] ([id])
GO
ALTER TABLE [product] CHECK CONSTRAINT [FK_product_brand]
GO
ALTER TABLE [product]  WITH CHECK ADD  CONSTRAINT [FK_product_category] FOREIGN KEY([category_id])
REFERENCES [category] ([id])
GO
ALTER TABLE [product] CHECK CONSTRAINT [FK_product_category]
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
