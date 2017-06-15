package zipkintrace.servicec.grpc.servicec.generated;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: servicec/servicec.proto")
public final class ServiceCGrpc {

  private ServiceCGrpc() {}

  public static final String SERVICE_NAME = "servicec.ServiceC";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty,
      zipkintrace.servicec.grpc.servicec.generated.Servicec.Result> METHOD_CALL_C =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "servicec.ServiceC", "callC"),
          io.grpc.protobuf.ProtoUtils.marshaller(zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(zipkintrace.servicec.grpc.servicec.generated.Servicec.Result.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServiceCStub newStub(io.grpc.Channel channel) {
    return new ServiceCStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceCBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ServiceCBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceCFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ServiceCFutureStub(channel);
  }

  /**
   */
  public static abstract class ServiceCImplBase implements io.grpc.BindableService {

    /**
     */
    public void callC(zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty request,
        io.grpc.stub.StreamObserver<zipkintrace.servicec.grpc.servicec.generated.Servicec.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CALL_C, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CALL_C,
            asyncUnaryCall(
              new MethodHandlers<
                zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty,
                zipkintrace.servicec.grpc.servicec.generated.Servicec.Result>(
                  this, METHODID_CALL_C)))
          .build();
    }
  }

  /**
   */
  public static final class ServiceCStub extends io.grpc.stub.AbstractStub<ServiceCStub> {
    private ServiceCStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServiceCStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceCStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServiceCStub(channel, callOptions);
    }

    /**
     */
    public void callC(zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty request,
        io.grpc.stub.StreamObserver<zipkintrace.servicec.grpc.servicec.generated.Servicec.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CALL_C, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ServiceCBlockingStub extends io.grpc.stub.AbstractStub<ServiceCBlockingStub> {
    private ServiceCBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServiceCBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceCBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServiceCBlockingStub(channel, callOptions);
    }

    /**
     */
    public zipkintrace.servicec.grpc.servicec.generated.Servicec.Result callC(zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CALL_C, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ServiceCFutureStub extends io.grpc.stub.AbstractStub<ServiceCFutureStub> {
    private ServiceCFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServiceCFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceCFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServiceCFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<zipkintrace.servicec.grpc.servicec.generated.Servicec.Result> callC(
        zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CALL_C, getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL_C = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ServiceCImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ServiceCImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL_C:
          serviceImpl.callC((zipkintrace.servicec.grpc.servicec.generated.Servicec.Empty) request,
              (io.grpc.stub.StreamObserver<zipkintrace.servicec.grpc.servicec.generated.Servicec.Result>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ServiceCDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return zipkintrace.servicec.grpc.servicec.generated.Servicec.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServiceCGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServiceCDescriptorSupplier())
              .addMethod(METHOD_CALL_C)
              .build();
        }
      }
    }
    return result;
  }
}
