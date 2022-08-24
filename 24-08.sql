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
	[first_name] [nvarchar](50) NULL,
	[last_name] [nvarchar](50) NULL,
	[email] [varchar](200) NULL,
	[password] [varchar](128) NULL,
	[role] [varchar](50) NULL,
	[enabled] [bit] NULL,
	[is_upgrade] [bit] NULL,
	[username] [varchar](50) NULL,
	[fullname] [nvarchar](50) NULL,
	[gender] [bit] NULL,
	[address] [nvarchar](255) NULL,
	[phone] [varchar](10) NULL,
	[image] [varchar](255) NULL,
	[provider] [varchar](50) NULL,
	[image_url] [varchar](255) NULL,
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
	[description] [varchar](500) NULL,
	[image] [varchar](200) NULL,
	[debut] [date] NULL,
	[country] [varchar](50) NULL,
 CONSTRAINT [PK__artist__3213E83FAB09C4EA] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [block_comment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](50) NULL,
 CONSTRAINT [PK_block_comment] PRIMARY KEY CLUSTERED 
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
	[date] [datetime] NULL,
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
CREATE TABLE [order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[unit_price] [float] NULL,
	[quantity] [int] NULL,
	[order_id] [int] NULL,
	[product_id] [int] NULL,
 CONSTRAINT [PK_order_detail] PRIMARY KEY CLUSTERED 
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
	[quantity] [int] NULL,
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
CREATE TABLE [promotion](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[discount] [int] NULL,
	[title] [nvarchar](255) NULL,
	[code] [varchar](255) NULL,
	[use_times] [int] NULL,
	[start_date] [date] NULL,
	[end_date] [date] NULL,
 CONSTRAINT [PK_promotion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [promotioncode](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](255) NULL,
	[use_times] [int] NULL,
	[promotion_id] [int] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_promotioncode] PRIMARY KEY CLUSTERED 
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
	[vip] [bit] NULL,
	[image] [varchar](500) NULL,
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
	[date] [date] NULL,
	[payment] [varchar](50) NULL,
	[status] [varchar](50) NULL,
	[account_id] [bigint] NULL,
	[total] [float] NULL,
	[promotion_id] [int] NULL,
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
	[song_order_id] [int] NULL,
	[song_id] [int] NULL,
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
CREATE TABLE [upgradeviporderdetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[duration] [int] NULL,
	[total] [float] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_upgradeviporderdetail] PRIMARY KEY CLUSTERED 
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
CREATE TABLE [viptoken](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[token] [varchar](255) NULL,
	[expiration_time] [datetime] NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_viptoken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [accounts] ON 

INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (1, N'Luu', N'Thanh', N'sluuthanh4@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_MODERATOR', 1, 0, N'mod', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (2, N'Luu', N'Thanh', N'sluuthanh1@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_ADMIN', 1, 1, N'admin', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (3, N'Luu', N'Thanh', N'sluuthanh2@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_EDITOR', 1, 1, N'editor', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (4, N'Luu', N'Thanh', N'sluuthanh3@gmail.com', N'$2a$10$HY56kmMJhw.vQwg1wrZFWuPg/Z7pMxCWvqKz4.bMB.Ic2AkZly7.W', N'ROLE_USER', 1, 0, N'user', N'Lưu Thanh Sang', NULL, NULL, NULL, N'3387752.png', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (5, N'Luu Th? Di?u', N'Linh', N'Dieulinhluu941@gmail.com', N'$2a$10$0cc5CzPeU.n1UqC50Cl7Iul8Cfisrng6Qbh3QYiE8REumEhd0L/52', N'ROLE_USER', 0, 0, N'Dieulinhluu941@gmail.com', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (6, N'nam', N'123', N'namndts2009031@fpt.edu.vn', N'$2a$10$m3vTPyA2iM.h8GoZsPpVBOMscfXjwBEyl1HUHYIXuE36aDEAOpNWa', N'ROLE_USER', 1, 0, N'namndts2009031@fpt.edu.vn', N'Nguyen Duc Nam', 0, N'', N'', N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (7, N'Luu', N'Thanh', N'namnguyendian97@gmail.com', N'$2a$10$PkJ525s4MiYn0gdnKhpUouw0/K5zp2FFyOhammY3S2nTBSyWXVhkm', N'ROLE_USER', 1, 0, N'namnguyendian97@gmail.com', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (8, N'Luu', N'Thanh', N'purpleminsuga@gmail.com', NULL, N'ROLE_USER', 1, 0, N'purpleminsuga@gmail.com', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', N'Google', NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (36, N'Luu', N'Thanh', N'sluuthanh@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_VIP', 1, 0, N'sluuthanh@gmail.com', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', N'Google', N'https://lh3.googleusercontent.com/a-/AFdZucrTrKJBF0EjLh4txv15FCS6lDhvdDLMOffVBUO0ew=s96-c')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (37, N'Luu', N'Thanh', N'sluuthanh.demo.send.email@gmail.com', NULL, N'ROLE_VIP', 1, 0, N'sluuthanh.demo.send.email@gmail.com', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', N'Google', N'https://lh3.googleusercontent.com/a/AItbvmmvfDz3kVO7rpY5UwyAFxq0jyQov03D9QLhedR0=s96-c')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (38, N'Luu', N'Thanh', N'sangltts2009006@fpt.edu.vn', NULL, N'ROLE_VIP', 1, 0, N'sangltts2009006@fpt.edu.vn', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', N'Google', N'https://lh3.googleusercontent.com/a/AItbvmnM52FH5dUTGgDGXWpSbOPHfcGFJ5DNvqBWMbqQ=s96-c')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (39, N'Luu', N'Thanh', N'dieulinhluu94@gmail.com', NULL, N'ROLE_VIP', 1, 0, N'dieulinhluu94@gmail.com', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', N'Google', N'https://lh3.googleusercontent.com/a-/AFdZucrRMnbmAFrXjC8N_rsEd6QPrHE1Vh8qO38Ss_EQ=s96-c')
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (44, NULL, NULL, NULL, N'$2a$10$4oSRvseQcghm4NPQVdLPNe1Jr3JNu7pRbE/xk7AtbGWCMgYA2QGXO', N'ROLE_USER', 1, 0, N'abc', N'luu huy hoang', 0, NULL, NULL, NULL, NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (45, NULL, NULL, NULL, N'$2a$10$0SzsfDqbTZZ9AnA9EcJOF.ZC4auwbtxRqF6pYacQcb2Sy95W8cwaW', N'ROLE_USER', 1, 0, N'cat', N'nguyen duc dung', 0, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (47, N'FPT', N'Nam', NULL, N'$2a$10$zBO96kkaRFDQOC9nO26z/.VQfWjaQ9mKqqDsD/MO9X9Mwmnqnh3W6', N'ROLE_ADMIN', 1, 0, N'namadmin', N'Nguyen Duc Nam', 0, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (48, N'Duc ', N'Nam', N'parkjungyeon1997@gmail.com', N'$2a$10$LIYNajYc.C93HgRPsuuCPOWFilv7K1GKFV6620ybntxPivMFklPmC', N'ROLE_USER', 1, 0, N'parkjungyeon1997@gmail.com', N'FPT Aptech', 0, NULL, NULL, N'user-default.png', NULL, NULL)
SET IDENTITY_INSERT [accounts] OFF
GO
SET IDENTITY_INSERT [album] ON 

INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (1, N'Sang1', 4, CAST(N'2019-05-26' AS Date), N'09.png')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (2, N'hello ', 4, CAST(N'2022-07-15' AS Date), N'nct.jpg')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (3, N'abc', 5, CAST(N'2022-08-19' AS Date), N'03.png')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (9, N'Anti', 10, CAST(N'2016-01-01' AS Date), N'07.png')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (10, N'Bangerz (Deluxe Version)', 13, CAST(N'2013-01-01' AS Date), N'01.png')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (11, N'Memories (Single)', 14, CAST(N'2019-01-01' AS Date), N'06.png')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (12, N'Unknowz', 9, CAST(N'2013-01-01' AS Date), N'02.png')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (13, N'Make A WIsh', 13, CAST(N'2022-08-10' AS Date), N'Maisie-Peters.jpg')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (14, N'MADE', 1, CAST(N'2015-03-09' AS Date), N'BIGBANG_-_MADE.jpg')
INSERT [album] ([id], [name], [artist_id], [release_date], [image]) VALUES (15, N'G Dragon ', 1, CAST(N'2022-08-19' AS Date), N'diem-lai-nhung-mv-solo-chat-nhat-qua-dat-cua-gd.jpg')
SET IDENTITY_INSERT [album] OFF
GO
SET IDENTITY_INSERT [artist] ON 

INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (1, N'BIGBANG', N'Big Bang (Korean: ??; stylized in all caps) is a South Korean boy band formed by YG Entertainment. ', N'b7-3756.png', CAST(N'1900-01-01' AS Date), N'KoreaSout')
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (4, N'NCT Dream', N'Debut:23/23/2022
asjdjadj ajdaj dajjdaj ', N'nct.jpg', NULL, NULL)
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (5, N'Red Velvet', N'asd
dasdas
asdad', N'09.jpg', CAST(N'2015-07-18' AS Date), N'StHelena')
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (7, N'Eminem', N'Marshall Bruce Mathers III, known professionally as Eminem, is an American rapper, songwriter and record producer. He is credited with popularizing hip hop in middle America and is critically acclaimed as one of the greatest rappers of all time. ', N'06.png', CAST(N'1988-01-01' AS Date), N'UnitedStatesofAmerica')
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (9, N'Selena Gomez', N'Selena Marie Gomez is an American singer, songwriter, and actress. Gomez began her acting career on the children''s television series Barney & Friends. In her teenage years, she rose to prominence for her lead role as Alex Russo in the Disney Channel television series Wizards of Waverly Place.', N'selena-gomez-10.webp', CAST(N'2002-01-01' AS Date), N'UnitedStatesofAmerica')
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (10, N'Rihanna', N'Popstar Rihanna signed with Def Jam records at age 16 and in 2005 released her first album Music of the Sun, which sold more than two million copies worldwide. She went on to release more albums and an array of hit songs, including "Unfaithful," "Umbrella," "Disturbia," "Take a Bow," "Diamonds" and "We Found Love." A global pop star with an unrelentingly edgy image, Rihanna has also won multiple industry accolades, including Grammys and MTV awards.', N'07.png', CAST(N'2005-01-01' AS Date), N'UnitedStatesofAmerica')
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (13, N'Miley Cyrus', N'Miley Cyrus, in full Miley Ray Cyrus, original name Destiny Hope Cyrus, (born November 23, 1992, Franklin, Tennessee, U.S.), American singer and actress whose performance on the television show Hannah Montana (2006–11) and its related soundtrack albums catapulted her into stardom.', N'Miley-Cyrus-2013.webp', CAST(N'2003-01-01' AS Date), N'UnitedStatesofAmerica')
INSERT [artist] ([id], [name], [description], [image], [debut], [country]) VALUES (14, N'Adam Levine (Maroon 5) ', N'Kara''s Flowers and the formation of Maroon 5. Adam Levine was introduced to Ryan Dusick by a mutual friend and guitarist, Adam Salzman. Levine was 15 years old, and Dusick was 16. Three of the five members of the band started playing together at age 12.', N'Maroon-5.jpg', CAST(N'1994-01-01' AS Date), N'UnitedStatesofAmerica')
SET IDENTITY_INSERT [artist] OFF
GO
SET IDENTITY_INSERT [brand] ON 

INSERT [brand] ([id], [name]) VALUES (1, N'Exoo')
INSERT [brand] ([id], [name]) VALUES (2, N'NMIX')
SET IDENTITY_INSERT [brand] OFF
GO
SET IDENTITY_INSERT [cart] ON 

INSERT [cart] ([id], [account_id], [amount]) VALUES (6, 2, 6)
INSERT [cart] ([id], [account_id], [amount]) VALUES (10, 1, 1)
INSERT [cart] ([id], [account_id], [amount]) VALUES (22, 37, 1)
INSERT [cart] ([id], [account_id], [amount]) VALUES (29, 36, 1)
INSERT [cart] ([id], [account_id], [amount]) VALUES (33, 48, 1)
SET IDENTITY_INSERT [cart] OFF
GO
SET IDENTITY_INSERT [cartitem] ON 

INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (9, 13, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (10, 11, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (11, 14, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (12, 18, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (14, 15, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (20, 15, 10)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (44, 12, 22)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (52, 20, 29)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (54, 35, 6)
INSERT [cartitem] ([id], [song_id], [cart_id]) VALUES (60, 43, 33)
SET IDENTITY_INSERT [cartitem] OFF
GO
SET IDENTITY_INSERT [category] ON 

INSERT [category] ([id], [name]) VALUES (1, N'FanLight')
INSERT [category] ([id], [name]) VALUES (2, N'Trong')
SET IDENTITY_INSERT [category] OFF
GO
SET IDENTITY_INSERT [comment] ON 

INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (91, N's', 2, 16, CAST(N'2022-08-05T19:27:08.547' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (92, N's', 2, 12, CAST(N'2022-08-06T22:33:39.440' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (93, N'hhhhh', 6, 17, CAST(N'2022-08-12T23:01:07.487' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (94, N'mmmm', 6, 17, CAST(N'2022-08-12T23:02:27.337' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (95, N'sss', 7, 18, CAST(N'2022-08-13T04:48:53.377' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (96, N'sss', 6, 19, CAST(N'2022-08-13T06:36:20.317' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (97, N'hello anh em', 6, 19, CAST(N'2022-08-13T06:36:29.587' AS DateTime))
INSERT [comment] ([id], [content], [account_id], [song_id], [datetime]) VALUES (99, N'kk', 4, 37, CAST(N'2022-08-24T17:18:22.947' AS DateTime))
SET IDENTITY_INSERT [comment] OFF
GO
SET IDENTITY_INSERT [download_allowed] ON 

INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1, 12, 2, NULL)
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (2, 19, 4, NULL)
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (3, 18, 4, NULL)
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (4, 15, 4, NULL)
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (5, 15, 6, CAST(N'2022-08-09T22:52:07.533' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (6, 18, 6, CAST(N'2022-08-09T22:52:08.183' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (7, 15, 7, CAST(N'2022-08-13T04:35:20.043' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (8, 18, 7, CAST(N'2022-08-13T04:35:20.083' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (9, 21, 7, CAST(N'2022-08-13T04:49:49.650' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (10, 20, 7, CAST(N'2022-08-13T04:57:13.290' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (11, 19, 7, CAST(N'2022-08-13T05:26:22.097' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (12, 11, 7, CAST(N'2022-08-13T05:26:22.123' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (13, 19, 6, CAST(N'2022-08-13T06:33:06.477' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (14, 11, 6, CAST(N'2022-08-13T06:38:29.330' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (15, 20, 6, CAST(N'2022-08-13T06:38:29.360' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (16, 21, 6, CAST(N'2022-08-13T06:38:29.370' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (17, 19, 8, CAST(N'2022-08-13T08:31:58.237' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (18, 14, 8, CAST(N'2022-08-13T08:31:58.400' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (19, 15, 8, CAST(N'2022-08-13T08:31:58.537' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (20, 18, 8, CAST(N'2022-08-13T08:31:59.120' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (21, 11, 36, CAST(N'2022-08-19T23:33:13.957' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (22, 11, 4, CAST(N'2022-08-21T06:14:26.813' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (23, 20, 4, CAST(N'2022-08-21T06:14:26.833' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (24, 21, 4, CAST(N'2022-08-21T07:05:16.627' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1024, 22, 4, CAST(N'2022-08-21T07:16:42.957' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1025, 14, 4, CAST(N'2022-08-23T18:18:35.160' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1026, 30, 4, CAST(N'2022-08-23T18:18:35.230' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1027, 36, 4, CAST(N'2022-08-24T05:35:48.387' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1028, 38, 4, CAST(N'2022-08-24T17:05:04.173' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1029, 24, 4, CAST(N'2022-08-24T17:05:04.220' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1030, 23, 4, CAST(N'2022-08-24T17:29:10.393' AS DateTime))
INSERT [download_allowed] ([id], [song_id], [account_id], [date]) VALUES (1031, 34, 4, CAST(N'2022-08-24T17:29:10.403' AS DateTime))
SET IDENTITY_INSERT [download_allowed] OFF
GO
SET IDENTITY_INSERT [genre] ON 

INSERT [genre] ([id], [name]) VALUES (1, N'Pop')
INSERT [genre] ([id], [name]) VALUES (2, N'Jazz')
INSERT [genre] ([id], [name]) VALUES (3, N'Ballad')
SET IDENTITY_INSERT [genre] OFF
GO
SET IDENTITY_INSERT [playlist] ON 

INSERT [playlist] ([id], [name], [account_id]) VALUES (2, N'dau bung', 4)
INSERT [playlist] ([id], [name], [account_id]) VALUES (3, N'socola', 4)
INSERT [playlist] ([id], [name], [account_id]) VALUES (6, N'jj', 2)
SET IDENTITY_INSERT [playlist] OFF
GO
SET IDENTITY_INSERT [playlistitem] ON 

INSERT [playlistitem] ([id], [song_id], [playlist_id]) VALUES (13, 12, 2)
INSERT [playlistitem] ([id], [song_id], [playlist_id]) VALUES (15, 11, 2)
INSERT [playlistitem] ([id], [song_id], [playlist_id]) VALUES (21, 15, 3)
SET IDENTITY_INSERT [playlistitem] OFF
GO
SET IDENTITY_INSERT [promotion] ON 

INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (5, 40, N'pro1', N'455025f6-8022-426b-aa5e-704d55d86968', 5, CAST(N'2022-08-10' AS Date), CAST(N'2022-08-19' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (8, 40, N'pro 4', N'98c86852-ea9a-4169-9575-c81d59401b98', 1, CAST(N'2022-08-17' AS Date), CAST(N'2022-08-19' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (9, 40, N'pro3', N'9a554784-8c2c-42fd-9cac-6f901d93d1c3', 2, CAST(N'2022-08-20' AS Date), CAST(N'2022-08-21' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (10, 40, N'pro5', N'e719e71e-e33f-4922-ad23-283474ea64ff', 5, CAST(N'2022-09-20' AS Date), CAST(N'2022-09-30' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (12, 40, N'pro123213', N'9b0ce462-cfd7-490f-9f86-a7f183b48738', 10, CAST(N'2022-08-27' AS Date), CAST(N'2022-09-20' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (13, 40, N'pro13', N'602ddf12-733a-4ce0-94a5-d099f0b1b84b', 5, CAST(N'2022-08-20' AS Date), CAST(N'2022-08-26' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (14, 40, N'pro34213', N'b64039e9-290e-45c1-b649-60600827543b', 3, CAST(N'2022-08-20' AS Date), CAST(N'2022-08-26' AS Date))
INSERT [promotion] ([id], [discount], [title], [code], [use_times], [start_date], [end_date]) VALUES (15, 10, N'Discount 10%', N'db594df9-8690-42c3-b3bb-30888c2b4698', 10, CAST(N'2022-08-24' AS Date), CAST(N'2022-08-31' AS Date))
SET IDENTITY_INSERT [promotion] OFF
GO
SET IDENTITY_INSERT [promotioncode] ON 

INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (1, N'9a554784-8c2c-42fd-9cac-6f901d93d1c3', 2, 9, 4)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (2, N'602ddf12-733a-4ce0-94a5-d099f0b1b84b', 2, 13, 4)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (3, N'b64039e9-290e-45c1-b649-60600827543b', 3, 14, 4)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (4, N'9a554784-8c2c-42fd-9cac-6f901d93d1c3', 0, 9, 36)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (5, N'602ddf12-733a-4ce0-94a5-d099f0b1b84b', 0, 13, 36)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (6, N'b64039e9-290e-45c1-b649-60600827543b', 3, 14, 36)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (7, N'602ddf12-733a-4ce0-94a5-d099f0b1b84b', 0, 13, 1)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (8, N'b64039e9-290e-45c1-b649-60600827543b', 0, 14, 1)
INSERT [promotioncode] ([id], [code], [use_times], [promotion_id], [user_id]) VALUES (9, N'db594df9-8690-42c3-b3bb-30888c2b4698', 1, 15, 4)
SET IDENTITY_INSERT [promotioncode] OFF
GO
SET IDENTITY_INSERT [song] ON 

INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (11, N'Chewing Gum', N'chewinggum.mp4', 2, 4, N'', 1, 35, N'chewinggum.mp4', NULL, 5, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (12, N'Everything', N'Everything Goes On - Porter Robinson (Official Music Video) - Star Guardian 2022.mp4', 2, 4, N'', 1, 67, N'Everything Goes On - Porter Robinson (Official Music Video) - Star Guardian 2022.mp4', NULL, NULL, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (13, N'Beless', N'BelessedCured.mp4', 2, 4, N'', 1, 92, N'BelessedCured.mp4', NULL, NULL, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (14, N'killng me', N'killingme.mp4', 2, 4, N'', 1, 15, N'killingme.mp4', NULL, 500, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (15, N'drunk dazed', N'Drunk-Dazed.mp4', 1, 4, N'', 1, 16, NULL, NULL, 6, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (16, N'My First And Last', N'My First and LastMV.mp4', 1, 4, N'', 1, 0, N'FuturePerfect.mp4', NULL, 5, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (17, N'future', N'diveintoyou.mp4', 1, 4, N'', 1, 0, N'FuturePerfect.mp4', NULL, 5, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (18, N'tamed', N'snakes.mp4', 1, 4, N'', 1, 0, N'tamed.mp4', NULL, 3, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (19, N'Dive Into You', N'diveintoyou.mp4', 2, 4, N'', 2, 3, N'diveintoyou.mp4', NULL, 5, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (20, N'asd', N'diveintoyou (2).mp4', 1, 4, N'', 1, 5, NULL, NULL, 3, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (21, N'22', N'diveintoyou (1).mp4', 1, 4, N'', 1, 8, NULL, NULL, 22, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (22, N'Angel Or Devil', N'angelordevil.mp4', 3, 5, N'', 2, 14, NULL, NULL, 5, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (23, N'abc', N'CSIL.mp3', 3, 5, N'', 1, 6, NULL, NULL, 555, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (24, N'asasasss', N'BMVD.mp3', 1, 4, N'', 1, 3, NULL, NULL, 5, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (25, N'Four Five Seconds', N'IFVO.mp3', 9, 10, N'', 2, 0, NULL, NULL, 10, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (26, N'Wrecking Ball', N'OQMC.mp3', 10, 13, N'', 2, 0, NULL, NULL, 10, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (29, N'Memories', N'Memories - Maroon 5.m4a', 11, 14, N'', 1, 1, NULL, NULL, 14, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (30, N'GirlsLikeYou', N'GirlsLikeYou-Maroon5CardiB.mp3', 11, 14, N'', 2, 0, NULL, NULL, 13, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (31, N'Who says', N'ICNK.mp3', 12, 9, N'', 2, 0, NULL, NULL, 5, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (34, N'Stand By Me', N'standbyme.mp4', 10, 13, N'', 1, 3, N'workit.mp4', NULL, 100, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (35, N'23232', N'GirlsLikeYou-Maroon5CardiB.mp3', 10, 13, N'', 2, 0, N'GirlsLikeYou-Maroon5CardiB.mp3', NULL, 23, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (36, N'Chance', N'chance.mp4', 10, 13, N'', 1, 0, NULL, NULL, 4, 1, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (37, N'hello future', N'hellofuture.mp4', 2, 4, N'', 1, 5, NULL, NULL, 4, NULL, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (38, N'zz', N'pinkvenom.mp4', 2, 4, N'', 1, 1, NULL, NULL, 6, 1, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (40, N'forever 1', N'forever 1.mp4', 10, 13, N'', 1, 1, NULL, NULL, 6, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (41, N'Blue', N'BIGBANG - BLUE M-V.mp4', 14, 1, N'', 1, 2, NULL, NULL, 5, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (42, N'Loser', N'BIGBANG - LOSER M-V.mp4', 14, 1, N'', 1, 3, NULL, NULL, 5, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (43, N'Crooked', N'wegoup.mp4', 15, 1, N'', 1, 1, NULL, NULL, 5, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (44, N'What Wrong', N'WHAT''S WRONGM-V (1).mp4', 15, 1, N'', 1, 4, NULL, NULL, 5, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (45, N'Sober ', N'sober2.mp4', 14, 1, N'', 1, 1, NULL, NULL, 4, 0, NULL)
INSERT [song] ([id], [name], [media], [album_id], [artist_id], [lyric], [genre_id], [view], [video], [account_id], [price], [vip], [image]) VALUES (46, N'Crooked', N'crooked.mp4', 15, 1, N'', 3, 0, NULL, NULL, 5, 0, NULL)
SET IDENTITY_INSERT [song] OFF
GO
SET IDENTITY_INSERT [song_order] ON 

INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (1, CAST(N'2022-08-13' AS Date), N'Paypal', N'Success', 7, 9, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (2, CAST(N'2022-08-12' AS Date), N'Paypal', N'Success', 7, 22, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (3, CAST(N'2022-08-12' AS Date), N'Paypal', N'Canceled', 7, 3, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (4, CAST(N'2022-08-12' AS Date), N'Paypal', N'Success', 7, 3, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (5, CAST(N'2022-08-12' AS Date), N'Paypal', N'Success', 7, 10, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (6, CAST(N'2022-08-12' AS Date), N'Paypal', N'Success', 6, 5, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (7, CAST(N'2022-08-12' AS Date), N'Paypal', N'Success', 6, 30, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (8, CAST(N'2022-08-13' AS Date), N'Paypal', N'Success', 8, 514, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (9, CAST(N'2022-08-13' AS Date), N'Paypal', N'Success', 6, 3, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (12, CAST(N'2022-08-17' AS Date), N'Paypal', N'Success', 37, 2, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (13, CAST(N'2022-08-18' AS Date), N'Paypal', N'Success', 38, 2, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (14, CAST(N'2022-08-18' AS Date), N'Paypal', N'Success', 39, 2, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (15, CAST(N'2022-08-19' AS Date), N'Paypal', N'Success', 36, 5, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (16, CAST(N'2022-08-20' AS Date), N'Paypal', N'Success', 4, 4.8, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (17, CAST(N'2022-08-21' AS Date), N'Paypal', N'Success', 4, 13.2, 5)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (1017, CAST(N'2022-08-21' AS Date), N'Paypal', N'Success', 4, 3, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (1018, CAST(N'2022-08-23' AS Date), N'Paypal', N'Success', 4, 513, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (1019, CAST(N'2022-08-23' AS Date), N'Paypal', N'Success', 4, 4, NULL)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (1020, CAST(N'2022-08-24' AS Date), N'Paypal', N'Success', 4, 6.6, 13)
INSERT [song_order] ([id], [date], [payment], [status], [account_id], [total], [promotion_id]) VALUES (1021, CAST(N'2022-08-24' AS Date), N'Paypal', N'Success', 4, 589.5, 15)
SET IDENTITY_INSERT [song_order] OFF
GO
SET IDENTITY_INSERT [song_order_detail] ON 

INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1, 1, 15)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (2, 1, 18)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (3, 2, 21)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (4, 3, 20)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (5, 4, 20)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (6, 5, 19)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (7, 5, 11)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (8, 6, 19)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (9, 7, 11)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (10, 7, 20)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (11, 7, 21)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (12, 8, 19)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (13, 8, 14)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (14, 8, 15)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (15, 8, 18)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (16, 15, 11)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (17, 16, 11)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (18, 16, 20)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (19, 17, 21)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1019, 1017, 22)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1020, 1018, 14)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1021, 1018, 30)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1022, 1019, 36)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1023, 1020, 38)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1024, 1020, 24)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1025, 1021, 23)
INSERT [song_order_detail] ([id], [song_order_id], [song_id]) VALUES (1026, 1021, 34)
SET IDENTITY_INSERT [song_order_detail] OFF
GO
SET IDENTITY_INSERT [upgradeviporderdetail] ON 

INSERT [upgradeviporderdetail] ([id], [duration], [total], [user_id]) VALUES (1, 3, NULL, 36)
INSERT [upgradeviporderdetail] ([id], [duration], [total], [user_id]) VALUES (2, 360, NULL, 36)
INSERT [upgradeviporderdetail] ([id], [duration], [total], [user_id]) VALUES (3, 3, 2, 37)
INSERT [upgradeviporderdetail] ([id], [duration], [total], [user_id]) VALUES (4, 3, 2, 38)
INSERT [upgradeviporderdetail] ([id], [duration], [total], [user_id]) VALUES (5, 3, 2, 39)
SET IDENTITY_INSERT [upgradeviporderdetail] OFF
GO
SET IDENTITY_INSERT [verificationtoken] ON 

INSERT [verificationtoken] ([id], [token], [expiration_time], [user_id]) VALUES (1, N'cd261af5-046b-4689-89fc-d051d2833153', CAST(N'2022-07-29T00:00:00.000' AS DateTime), 5)
INSERT [verificationtoken] ([id], [token], [expiration_time], [user_id]) VALUES (2, N'67f2fdf2-4683-408b-bb71-ce94d1d4a5af', CAST(N'2022-08-05T12:39:56.310' AS DateTime), 7)
SET IDENTITY_INSERT [verificationtoken] OFF
GO
SET IDENTITY_INSERT [viptoken] ON 

INSERT [viptoken] ([id], [token], [expiration_time], [user_id]) VALUES (1, N'34936a83-4ef8-4e23-85d7-1291a82467a9', CAST(N'2022-08-15T19:35:49.010' AS DateTime), 6)
INSERT [viptoken] ([id], [token], [expiration_time], [user_id]) VALUES (2, N'72119feb-3eb7-4fa7-b78e-b055efc571ed', CAST(N'2022-08-20T22:49:22.057' AS DateTime), 36)
INSERT [viptoken] ([id], [token], [expiration_time], [user_id]) VALUES (3, N'5e53aba2-72a1-4a06-beb2-df5368a0ca31', CAST(N'2023-08-12T22:51:00.483' AS DateTime), 36)
INSERT [viptoken] ([id], [token], [expiration_time], [user_id]) VALUES (4, N'53645976-c3c7-4f82-baac-b88c2cb90efc', CAST(N'2022-08-20T23:07:18.527' AS DateTime), 37)
INSERT [viptoken] ([id], [token], [expiration_time], [user_id]) VALUES (5, N'c7386230-9d12-4119-9473-e770afa3c44d', CAST(N'2022-08-21T13:26:35.977' AS DateTime), 38)
INSERT [viptoken] ([id], [token], [expiration_time], [user_id]) VALUES (6, N'4265b448-0f33-4e72-a47d-8cedde30551d', CAST(N'2022-08-21T13:35:05.843' AS DateTime), 39)
SET IDENTITY_INSERT [viptoken] OFF
GO
ALTER TABLE [album]  WITH CHECK ADD  CONSTRAINT [FK__album__artist_id__73BA3083] FOREIGN KEY([artist_id])
REFERENCES [artist] ([id])
GO
ALTER TABLE [album] CHECK CONSTRAINT [FK__album__artist_id__73BA3083]
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
ALTER TABLE [order_detail]  WITH CHECK ADD  CONSTRAINT [FK_order_detail_orders] FOREIGN KEY([order_id])
REFERENCES [orders] ([id])
GO
ALTER TABLE [order_detail] CHECK CONSTRAINT [FK_order_detail_orders]
GO
ALTER TABLE [order_detail]  WITH CHECK ADD  CONSTRAINT [FK_order_detail_product] FOREIGN KEY([product_id])
REFERENCES [product] ([id])
GO
ALTER TABLE [order_detail] CHECK CONSTRAINT [FK_order_detail_product]
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
ALTER TABLE [promotioncode]  WITH CHECK ADD  CONSTRAINT [FK_promotioncode_accounts] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [promotioncode] CHECK CONSTRAINT [FK_promotioncode_accounts]
GO
ALTER TABLE [promotioncode]  WITH CHECK ADD  CONSTRAINT [FK_promotioncode_promotion] FOREIGN KEY([promotion_id])
REFERENCES [promotion] ([id])
GO
ALTER TABLE [promotioncode] CHECK CONSTRAINT [FK_promotioncode_promotion]
GO
ALTER TABLE [song]  WITH CHECK ADD FOREIGN KEY([album_id])
REFERENCES [album] ([id])
GO
ALTER TABLE [song]  WITH CHECK ADD  CONSTRAINT [FK__song__artist_id__04E4BC85] FOREIGN KEY([artist_id])
REFERENCES [artist] ([id])
GO
ALTER TABLE [song] CHECK CONSTRAINT [FK__song__artist_id__04E4BC85]
GO
ALTER TABLE [song]  WITH CHECK ADD FOREIGN KEY([genre_id])
REFERENCES [genre] ([id])
GO
ALTER TABLE [song]  WITH CHECK ADD  CONSTRAINT [FK_song_accounts] FOREIGN KEY([account_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [song] CHECK CONSTRAINT [FK_song_accounts]
GO
ALTER TABLE [song_order]  WITH CHECK ADD  CONSTRAINT [FK_song_order_promotion] FOREIGN KEY([promotion_id])
REFERENCES [promotion] ([id])
GO
ALTER TABLE [song_order] CHECK CONSTRAINT [FK_song_order_promotion]
GO
ALTER TABLE [subtitle]  WITH CHECK ADD FOREIGN KEY([song_id])
REFERENCES [song] ([id])
GO
ALTER TABLE [upgradeviporderdetail]  WITH CHECK ADD  CONSTRAINT [FK_upgradeviporderdetail_accounts] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [upgradeviporderdetail] CHECK CONSTRAINT [FK_upgradeviporderdetail_accounts]
GO
ALTER TABLE [verificationtoken]  WITH CHECK ADD  CONSTRAINT [FK_USER_VERIFY_TOKEN] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [verificationtoken] CHECK CONSTRAINT [FK_USER_VERIFY_TOKEN]
GO
ALTER TABLE [viptoken]  WITH CHECK ADD  CONSTRAINT [FK_viptoken_accounts] FOREIGN KEY([user_id])
REFERENCES [accounts] ([id])
GO
ALTER TABLE [viptoken] CHECK CONSTRAINT [FK_viptoken_accounts]
GO
USE [master]
GO
ALTER DATABASE [MusicStore] SET  READ_WRITE 
GO
