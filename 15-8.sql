USE [master]
GO
DROP DATABASE [MusicStore]
CREATE DATABASE [MusicStore]
 
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
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
	[image] [varchar](500) NULL,
	[account_id] [bigint] NULL,
	[price] [int] NULL,
	[vip] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [song_order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [datetime] NULL,
	[payment] [varchar](50) NULL,
	[status] [varchar](50) NULL,
	[account_id] [bigint] NULL,
	[total] [float] NULL,
 CONSTRAINT [PK_song_order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]
) ON [PRIMARY]
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
ALTER TABLE [subtitle]  WITH CHECK ADD FOREIGN KEY([song_id])
REFERENCES [song] ([id])
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
