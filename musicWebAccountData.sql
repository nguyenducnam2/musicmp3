USE [MusicStore]
GO
SET IDENTITY_INSERT [dbo].[accounts] ON 

INSERT [dbo].[accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (1, N'Luu', N'Thanh', N'sluuthanh4@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_MODERATOR', 1, 0, N'mod', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [dbo].[accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (2, N'Luu', N'Thanh', N'sluuthanh1@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_ADMIN', 1, 1, N'admin', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [dbo].[accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (3, N'Luu', N'Thanh', N'sluuthanh2@gmail.com', N'$2a$10$fjqzswy30gZoO9YWB62zj.ySTw2iH7BdyNBLHR4m5bqMKVeuSjXRO', N'ROLE_EDITOR', 1, 1, N'editor', N'Lưu Thanh Sang', NULL, NULL, NULL, N'cat.jpg', NULL, NULL)
INSERT [dbo].[accounts] ([id], [first_name], [last_name], [email], [password], [role], [enabled], [is_upgrade], [username], [fullname], [gender], [address], [phone], [image], [provider], [image_url]) VALUES (4, N'Luu', N'Thanh', N'sluuthanh3@gmail.com', N'$2a$10$HY56kmMJhw.vQwg1wrZFWuPg/Z7pMxCWvqKz4.bMB.Ic2AkZly7.W', N'ROLE_USER', 1, 0, N'user', N'Lưu Thanh Sang', NULL, NULL, NULL, N'3387752.png', NULL, NULL)
SET IDENTITY_INSERT [dbo].[accounts] OFF
GO
