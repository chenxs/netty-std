package prv.chenxs.std.nettystd.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyTimeClient {
	public void connect(String host,int port) throws InterruptedException{
		EventLoopGroup group=new NioEventLoopGroup();
		try{
		Bootstrap b=new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel arg0) throws Exception {
				arg0.pipeline().addLast(new NettyTimentClientHandler());
				
			}
		});
		
		ChannelFuture future=b.connect(host, port).sync();
		
		future.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		try {
			new NettyTimeClient().connect("localhost",8080);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
