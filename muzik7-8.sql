USE [master]
GO
CREATE DATABASE [MusicStore]

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
	[gender] [bit] NULL,
	[address] [varchar](255) NULL,
	[phone] [varchar](10) NULL,
	[image] [varchar](255) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cart](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [bigint] NULL,
	[amount] [int] NULL,
 CONSTRAINT [PK_cart] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cartitem](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[song_id] [int] NULL,
	[cart_id] [int] NULL,
 CONSTRAINT [PK_cartitem] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
	[datetime] [datetime] NULL,
 CONSTRAINT [PK_comment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [download_allowed](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[song_id] [int] NULL,
	[account_id] [bigint] NULL,
 CONSTRAINT [PK_download_allowed] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [news](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](500) NULL,
	[content] [nvarchar](500) NULL,
	[description] [nvarchar](500) NULL,
	[image] [nvarchar](500) NULL,
	[img_title] [nvarchar](500) NULL,
	[view] [int] NULL,
	[created_at] [nvarchar](500) NULL,
 CONSTRAINT [PK_news] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[unit_price] [float] NULL,
	[discount] [float] NULL,
	[order_id] [int] NULL,
	[product_id] [int] NULL,
 CONSTRAINT [PK_orderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_date] [varchar](20) NULL,
	[phone_number] [varchar](20) NULL,
	[address] [varchar](100) NULL,
	[amount] [float] NULL,
	[description] [varchar](1000) NULL,
	[status] [int] NULL,
	[is_payment] [int] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_orders] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [passwordresettoken](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[token] [varchar](255) NULL,
	[expiration_time] [datetime] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_PasswordResetToken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[price] [money] NULL,
	[image] [varchar](200) NULL,
	[description] [varchar](500) NULL,
	[brand_id] [int] NULL,
	[category_id] [int] NULL,
	[created] [datetime] NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
	[account_id] [bigint] NULL,
	[price] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [song_order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[payment] [varchar](50) NULL,
	[datetime] [datetime] NULL,
	[status] [int] NULL,
	[account_id] [bigint] NULL,
 CONSTRAINT [PK_song_order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [song_order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[song_id] [int] NULL,
	[song_order_id] [int] NULL,
 CONSTRAINT [PK_song_order_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [verificationtoken](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[token] [varchar](255) NULL,
	[expiration_time] [datetime] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_VerificationToken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [warehouse](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_warehouse] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [accounts] ON 

INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (1, N'Luu', N'Thanh', N'sluuthanh@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_MODERATOR', 1, 1, N'mod', N'Sang', NULL, NULL, NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (2, N'Luu', N'Thanh', N'sluuthanh1@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_ADMIN', 1, 1, N'admin', N'Sang', NULL, NULL, NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (3, N'Luu', N'Thanh', N'sluuthanh2@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_EDITOR', 1, 1, N'editor', N'Sang', NULL, NULL, NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (4, N'Luu', N'Thanh', N'sluuthanh3@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_USER', 1, 0, N'user', NULL, NULL, N'', N'', N'nct.jpg')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (5, N'Luu Th? Di?u', N'Linh', N'Dieulinhluu941@gmail.com', N'$2a$10$0cc5CzPeU.n1UqC50Cl7Iul8Cfisrng6Qbh3QYiE8REumEhd0L/52', N'ROLE_USER', 0, 0, N'Dieulinhluu941@gmail.com', NULL, NULL, NULL, NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (6, N'nam', N'nguyen', N'namndts2009031@fpt.edu.vn', N'$2a$10$m3vTPyA2iM.h8GoZsPpVBOMscfXjwBEyl1HUHYIXuE36aDEAOpNWa', N'ROLE_USER', 1, 0, N'namndts2009031@fpt.edu.vn', NULL, NULL, N'', N'', N'images.png')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_edited], [username], [fullname], [gender], [address], [phone], [image]) VALUES (7, N'123', N'456', N'namnguyendian97@gmail.com', N'$2a$10$PkJ525s4MiYn0gdnKhpUouw0/K5zp2FFyOhammY3S2nTBSyWXVhkm', N'ROLE_USER', 0, 0, N'namnguyendian97@gmail.com', NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [accounts] OFF
GO
SET IDENTITY_INSERT [album] ON 

INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (1, N'Sang1', 4, CAST(N'2019-05-26' AS Date), N'Maisie-Peters.jpg')
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
SET IDENTITY_INSERT [cart] ON 

INSERT [cart] ([id], [account_id], [amount]) VALUES (6, 2, 5)
INSERT [cart] ([id], [account_id], [amount]) VALUES (9, 6, 1)
INSERT [cart] ([id], [account_id], [amount]) VALUES (10, 1, 1)
INSERT [cart] ([id], [account_id], [amount]) VALUES (11, 4, 3)
SET IDENTITY_INSERT [cart] OFF
GO
SET IDENTITY_INSERT [cartitem] ON 

INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (9, 1013, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (10, 1011, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (11, 1014, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (12, 1018, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (14, 1015, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (19, 1011, 9)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (20, 1015, 10)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (23, 1015, 11)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (24, 1014, 11)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (25, 1013, 11)
SET IDENTITY_INSERT [cartitem] OFF
GO
SET IDENTITY_INSERT [category] ON 

INSERT [category] ([id], [name]) VALUES (1, N'FanLight')
INSERT [category] ([id], [name]) VALUES (2, N'Trong')
SET IDENTITY_INSERT [category] OFF
GO
SET IDENTITY_INSERT [comment] ON 

INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (1085, N'hay qua', 2, 1011, CAST(N'2022-08-02T21:02:03.930' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (1086, N'tuyet', 6, 1011, CAST(N'2022-08-02T21:02:43.037' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (1088, N'hay ne', 2, 1013, CAST(N'2022-08-05T18:37:18.117' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (1091, N's', 2, 1016, CAST(N'2022-08-05T19:27:08.547' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (1092, N's', 2, 1012, CAST(N'2022-08-06T22:33:39.440' AS DateTime))
SET IDENTITY_INSERT [comment] OFF
GO
SET IDENTITY_INSERT [download_allowed] ON 

INSERT [download_allowed] ([id], [song_id], [account_id]) VALUES (1, 1012, 2)
SET IDENTITY_INSERT [download_allowed] OFF
GO
SET IDENTITY_INSERT [genre] ON 

INSERT [genre] ([id], [name]) VALUES (1, N'Pop')
INSERT [genre] ([id], [name]) VALUES (2, N'Jazz')
SET IDENTITY_INSERT [genre] OFF
GO
SET IDENTITY_INSERT [playlist] ON 

INSERT [playlist] ([id], [name], [account_id]) VALUES (2, N'dau bung', 4)
INSERT [playlist] ([id], [name], [account_id]) VALUES (3, N'socola', 4)
INSERT [playlist] ([id], [name], [account_id]) VALUES (6, N'jj', 2)
SET IDENTITY_INSERT [playlist] OFF
GO
SET IDENTITY_INSERT [playlistitem] ON 

INSERT [playlistitem] ([id], [song_id], [playlist_id]) VALUES (13, 1012, 2)
INSERT [playlistitem] ([id], [song_id], [playlist_id]) VALUES (15, 1011, 2)
INSERT [playlistitem] ([id], [song_id], [playlist_id]) VALUES (21, 1015, 3)
SET IDENTITY_INSERT [playlistitem] OFF
GO
SET IDENTITY_INSERT [song] ON 

INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1011, N'Chewing Gum', N'chewinggum.mp4', 2, 4, N'', 1, 30, N'chewinggum.mp4', NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1012, N'Everything', N'Everything Goes On - Porter Robinson (Official Music Video) - Star Guardian 2022.mp4', 2, 4, N'', 1, 54, N'Everything Goes On - Porter Robinson (Official Music Video) - Star Guardian 2022.mp4', NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1013, N'Beless', N'BelessedCured.mp4', 2, 4, N'', 1, 39, N'BelessedCured.mp4', NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1014, N'killng me', N'killingme.mp4', 2, 4, N'', 1, 7, N'killingme.mp4', NULL, 500)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1015, N'drunk dazed', N'Drunk-Dazed.mp4', 1, 4, N'', 1, 24, NULL, NULL, 600)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1016, N'future', N'FuturePerfect.mp4', 1, 4, N'', 1, 11, N'FuturePerfect.mp4', NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1017, N'future', N'Beatbox - NCT Dream.mp3', 1, 4, N'', 1, 3, N'FuturePerfect.mp4', NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price]) VALUES (1018, N'tamed', N'tamed.mp4', 1, 4, N'', 1, 4, N'tamed.mp4', NULL, NULL)
SET IDENTITY_INSERT [song] OFF
GO
SET IDENTITY_INSERT [verificationtoken] ON 

INSERT [verificationtoken] ([id], [token], [expiration_time], [user_id]) VALUES (1, N'cd261af5-046b-4689-89fc-d051d2833153', CAST(N'2022-07-29T00:00:00.000' AS DateTime), 5)
INSERT [verificationtoken] ([id], [token], [expiration_time], [user_id]) VALUES (2, N'67f2fdf2-4683-408b-bb71-ce94d1d4a5af', CAST(N'2022-08-05T12:39:56.310' AS DateTime), 7)
SET IDENTITY_INSERT [verificationtoken] OFF
GO
ALTER TABLE [album]  WITH CHECK ADD FOREIGN KEY([artist_id])
REFERENCES [artist] ([id])
GO
ALTER TABLE [cart]  WITH CHECK ADD  CONSTRAINT [FK_cart_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [cart] CHECK CONSTRAINT [FK_cart_accounts]
GO
ALTER TABLE [cartitem]  WITH CHECK ADD  CONSTRAINT [FK_cartitem_cart] FOREIGN KEY([cart_id])
REFERENCES [cart] ([id])
GO
ALTER TABLE [cartitem] CHECK CONSTRAINT [FK_cartitem_cart]
GO
ALTER TABLE [cartitem]  WITH CHECK ADD  CONSTRAINT [FK_cartitem_song] FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [cartitem] CHECK CONSTRAINT [FK_cartitem_song]
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
ALTER TABLE [download_allowed]  WITH CHECK ADD  CONSTRAINT [FK_download_allowed_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [download_allowed] CHECK CONSTRAINT [FK_download_allowed_accounts]
GO
ALTER TABLE [download_allowed]  WITH CHECK ADD  CONSTRAINT [FK_download_allowed_song] FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [download_allowed] CHECK CONSTRAINT [FK_download_allowed_song]
GO
ALTER TABLE [orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_orderDetail_orders] FOREIGN KEY([order_id])
REFERENCES [orders] ([id])
GO
ALTER TABLE [orderDetail] CHECK CONSTRAINT [FK_orderDetail_orders]
GO
ALTER TABLE [orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_orderDetail_product] FOREIGN KEY([product_id])
REFERENCES [product] ([id])
GO
ALTER TABLE [orderDetail] CHECK CONSTRAINT [FK_orderDetail_product]
GO
ALTER TABLE [orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_accounts] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [orders] CHECK CONSTRAINT [FK_orders_accounts]
GO
ALTER TABLE [passwordresettoken]  WITH CHECK ADD  CONSTRAINT [FK_USER_PASSWORD_TOKEN] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [passwordresettoken] CHECK CONSTRAINT [FK_USER_PASSWORD_TOKEN]
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
ALTER TABLE [song]  WITH CHECK ADD  CONSTRAINT [FK_song_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [song] CHECK CONSTRAINT [FK_song_accounts]
GO
ALTER TABLE [song_order]  WITH CHECK ADD  CONSTRAINT [FK_song_order_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [song_order] CHECK CONSTRAINT [FK_song_order_accounts]
GO
ALTER TABLE [song_order_detail]  WITH CHECK ADD  CONSTRAINT [FK_song_order_detail_song] FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [song_order_detail] CHECK CONSTRAINT [FK_song_order_detail_song]
GO
ALTER TABLE [song_order_detail]  WITH CHECK ADD  CONSTRAINT [FK_song_order_detail_song_order] FOREIGN KEY([song_order_id])
REFERENCES [song_order] ([id])
GO
ALTER TABLE [song_order_detail] CHECK CONSTRAINT [FK_song_order_detail_song_order]
GO
ALTER TABLE [subtitle]  WITH CHECK ADD FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [verificationtoken]  WITH CHECK ADD  CONSTRAINT [FK_USER_VERIFY_TOKEN] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [verificationtoken] CHECK CONSTRAINT [FK_USER_VERIFY_TOKEN]
GO
ALTER TABLE [warehouse]  WITH CHECK ADD  CONSTRAINT [FK_warehouse_product] FOREIGN KEY([product_id])
REFERENCES [product] ([id])
GO
ALTER TABLE [warehouse] CHECK CONSTRAINT [FK_warehouse_product]
GO
USE [master]
GO
ALTER DATABASE [MusicStore] SET  READ_WRITE 
GO
