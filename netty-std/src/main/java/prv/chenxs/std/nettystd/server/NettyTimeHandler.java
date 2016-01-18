package prv.chenxs.std.nettystd.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class NettyTimeHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		arg0.pipeline().addLast(new NettyTimeServerHandler());
	}

}
