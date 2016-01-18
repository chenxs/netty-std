package prv.chenxs.std.nettystd.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyTcpTimeServer {
	public void bind(int port) throws InterruptedException {
		EventLoopGroup bossGrop = new NioEventLoopGroup();
		EventLoopGroup workGrop = new NioEventLoopGroup();
		try {
			ServerBootstrap serBoot = new ServerBootstrap();
			serBoot.group(bossGrop, workGrop)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new NettyTcpTimeHandler())
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture future = serBoot.bind(port).sync();

			future.channel().closeFuture().sync();
		} finally {
			bossGrop.shutdownGracefully();
			workGrop.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		try {
			new NettyTcpTimeServer().bind(8080);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
