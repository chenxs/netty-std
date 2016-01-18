package prv.chenxs.std.nettystd.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class NettyTcpTimeHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		arg0.pipeline().addLast(new NettyTcpTimeServerHandler());
	}

}
