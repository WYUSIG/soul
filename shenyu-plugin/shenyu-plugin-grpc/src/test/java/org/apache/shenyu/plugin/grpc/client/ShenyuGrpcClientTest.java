package org.apache.shenyu.plugin.grpc.client;

import com.google.api.client.json.Json;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import io.grpc.CallOptions;
import io.grpc.ManagedChannel;
import org.apache.shenyu.common.constant.Constants;
import org.apache.shenyu.common.dto.MetaData;
import org.apache.shenyu.common.dto.SelectorData;
import org.apache.shenyu.common.enums.RpcTypeEnum;
import org.apache.shenyu.plugin.grpc.GrpcPlugin;
import org.apache.shenyu.plugin.grpc.cache.ApplicationConfigCache;
import org.apache.shenyu.plugin.grpc.cache.GrpcClientCache;
import org.apache.shenyu.plugin.grpc.reflection.ShenyuGrpcReflectionClient;
import org.apache.shenyu.plugin.grpc.resolver.ServiceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * The Test Case For {@link ShenyuGrpcClient}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuGrpcClientTest {

    private ShenyuGrpcClient shenyuGrpcClient;

    @Mock
    private ManagedChannel channel;

    private String serviceName = "echo";

    private MetaData metaData;

    String resultJson;

    @Before
    public void setUp() throws Exception {
        metaData = new MetaData();
        metaData.setId("1332017977771636096");
        metaData.setAppName("grpc");
        metaData.setContextPath("/grpc");
        metaData.setPath("/grpc/echo");
        metaData.setServiceName("echo.EchoService");
        metaData.setMethodName("echo");
        metaData.setRpcType(RpcTypeEnum.GRPC.getName());
        metaData.setRpcExt("{timeout:5000}");
        metaData.setEnabled(true);
        SelectorData selector = mock(SelectorData.class);
        when(selector.getName()).thenReturn("/grpc");
        when(selector.getHandle()).thenReturn("[{\"upstreamUrl\":\"localhost:8080\",\"weight\":50,\"status\":true}]");
        ApplicationConfigCache.getInstance().initPrx(selector);
        GrpcClientCache.initGrpcClient(selector.getName());

        shenyuGrpcClient = GrpcClientCache.getGrpcClient(selector.getName());

        resultJson = "file {\n" +
                "  name: \"echo.proto\"\n" +
                "  package: \"echo\"\n" +
                "  message_type {\n" +
                "    name: \"EchoRequest\"\n" +
                "    field {\n" +
                "      name: \"message\"\n" +
                "      number: 1\n" +
                "      label: LABEL_OPTIONAL\n" +
                "      type: TYPE_STRING\n" +
                "    }\n" +
                "  }\n" +
                "  message_type {\n" +
                "    name: \"EchoResponse\"\n" +
                "    field {\n" +
                "      name: \"message\"\n" +
                "      number: 1\n" +
                "      label: LABEL_OPTIONAL\n" +
                "      type: TYPE_STRING\n" +
                "    }\n" +
                "    field {\n" +
                "      name: \"traces\"\n" +
                "      number: 2\n" +
                "      label: LABEL_REPEATED\n" +
                "      type: TYPE_MESSAGE\n" +
                "      type_name: \".echo.Trace\"\n" +
                "    }\n" +
                "  }\n" +
                "  message_type {\n" +
                "    name: \"Trace\"\n" +
                "    field {\n" +
                "      name: \"host\"\n" +
                "      number: 1\n" +
                "      label: LABEL_OPTIONAL\n" +
                "      type: TYPE_STRING\n" +
                "    }\n" +
                "  }\n" +
                "  service {\n" +
                "    name: \"EchoService\"\n" +
                "    method {\n" +
                "      name: \"echo\"\n" +
                "      input_type: \".echo.EchoRequest\"\n" +
                "      output_type: \".echo.EchoResponse\"\n" +
                "      options {\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "  options {\n" +
                "    java_multiple_files: true\n" +
                "  }\n" +
                "  syntax: \"proto3\"\n" +
                "}";
//        Gson gson = new Gson();
//        DescriptorProtos.FileDescriptorSet fileDescriptorSet = gson.fromJson(resultJson, DescriptorProtos.FileDescriptorSet .class);
//        Field field = shenyuGrpcClient.getClass().getDeclaredField("reflectionClient");
//        field.setAccessible(true);
//        ShenyuGrpcReflectionClient reflectionClient = mock(ShenyuGrpcReflectionClient.class);
//        field.set(shenyuGrpcClient, reflectionClient);
//        when(reflectionClient.lookupService(serviceName)).thenReturn(mock(ListenableFuture.class));
//        when(reflectionClient.resolveService(serviceName)).thenReturn(fileDescriptorSet);

//        ServiceResolver serviceResolver = mock(ServiceResolver.class);
//        when(ServiceResolver.fromFileDescriptorSet(any())).thenReturn(serviceResolver);
//        Descriptors.MethodDescriptor methodDescriptor = mock(Descriptors.MethodDescriptor.class);
//        when(serviceResolver.resolveServiceMethod(any())).thenReturn(methodDescriptor);
    }

//    @Test
//    public void call() {
//        CallOptions callOptions = CallOptions.DEFAULT.withDeadlineAfter(5000, TimeUnit.MILLISECONDS);;
//        String requestJsons = "{'message':'1'}";
//        shenyuGrpcClient.call(metaData, callOptions, requestJsons);
////        verify(shenyuGrpcClient).call(metaData, callOptions, requestJsons);
//    }
}
