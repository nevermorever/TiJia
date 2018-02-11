package com.zyjd.tijia.api;

import com.zyjd.tijia.entity.CallRecord;
import com.zyjd.tijia.entity.Elevator;
import com.zyjd.tijia.entity.ElevatorRealtimeData;
import com.zyjd.tijia.entity.FaultRecord;
import com.zyjd.tijia.entity.File;
import com.zyjd.tijia.entity.Login;
import com.zyjd.tijia.entity.MaintenancePlan;
import com.zyjd.tijia.entity.MaintenanceRecord;
import com.zyjd.tijia.entity.PaginatedResult;
import com.zyjd.tijia.entity.RepairRecord;
import com.zyjd.tijia.entity.Terminal;
import com.zyjd.tijia.entity.Token;
import com.zyjd.tijia.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {
    // users
    @POST("login/")
    Observable<Token> login(@Body Login login);

    @POST("verify-jwt-token/")
    Observable<ResponseBody> verifyToken(@Body Token token);

    @GET("users/")
    Observable<PaginatedResult<User>> getUserList(@QueryMap Map<String, Object> map);

    @POST("users/")
    Observable<User> addUser(@Body User user);

    @GET("users/{username}/")
    Observable<User> getUserByUsername(@Path("username") String username);

    @PUT("users/{username}/")
    Observable<User> modifyUserByUsername(@Path("username") String username, @Body User user);

    @DELETE("users/{username}/")
    Observable<ResponseBody> deleteUserByUsername(@Path("username") String username);

    // repair-records
    @GET("repair-records/")
    Observable<PaginatedResult<RepairRecord>> getRepairRecordList(@QueryMap Map<String, Object> map);

    @POST("repair-records/")
    Observable<RepairRecord> addRepairRecord(@Body RepairRecord repairRecord);

    @GET("repair-records/{id}/")
    Observable<RepairRecord> getRepairRecordById(@Path("id") int id);

    @PUT("repair-records/{id}/")
    Observable<RepairRecord> modifyRepairRecordById(@Path("id") int id, @Body RepairRecord repairRecord);

    @DELETE("repair-records/{id}/")
    Observable<ResponseBody> deleteRepairRecordById(@Path("id") int id);

    // fault-records
    @GET("fault-records/")
    Observable<PaginatedResult<FaultRecord>> getFaultRecordList(@QueryMap Map<String, Object> map);

    @POST("repair-records/")
    Observable<FaultRecord> addFaultRecord(@Body FaultRecord faultRecord);

    @GET("repair-records/{id}/")
    Observable<FaultRecord> getFaultRecordById(@Path("id") int id);

    @PUT("repair-records/{id}/")
    Observable<FaultRecord> modifyFaultRecordById(@Path("id") int id, @Body FaultRecord faultRecord);

    @DELETE("repair-records/{id}/")
    Observable<ResponseBody> deleteFaultRecordById(@Path("id") int id);

    // maintenance-records
    @GET("maintenance-records/")
    Observable<PaginatedResult<MaintenanceRecord>> getMaintenanceRecordList(@QueryMap Map<String, Object> map);

    @POST("maintenance-records/")
    Observable<MaintenanceRecord> addMaintenanceRecord(@Body MaintenanceRecord maintenanceRecord);

    @GET("maintenance-records/{id}/")
    Observable<MaintenanceRecord> getMaintenanceRecordById(@Path("id") int id);

    @PUT("maintenance-records/{id}/")
    Observable<MaintenanceRecord> modifyMaintenanceRecordById(@Path("id") int id, @Body MaintenanceRecord maintenanceRecord);

    @DELETE("maintenance-records/{id}/")
    Observable<ResponseBody> deleteMaintenanceRecordById(@Path("id") int id);

    // maintenance-plans
    @GET("maintenance-plans/")
    Observable<List<MaintenancePlan>> getMaintenancePlanList(@QueryMap Map<String, Object> map);

    @GET("maintenance-plans/latest-plan-date/")
    Observable<List<MaintenancePlan>> getMaintenancePlanDateList(@QueryMap Map<String, Object> map);

    // call-records
    @GET("call-records/")
    Observable<PaginatedResult<CallRecord>> getCallRecordList(@QueryMap Map<String, Object> map);

    @POST("call-records/")
    Observable<CallRecord> addCallRecord(@Body CallRecord callRecord);

    @GET("call-records/{id}/")
    Observable<CallRecord> getCallRecordById(@Path("id") int id);

    @PUT("call-records/{id}/")
    Observable<CallRecord> modifyCallRecordById(@Path("id") int id, @Body CallRecord callRecord);

    @DELETE("call-records/{id}/")
    Observable<ResponseBody> deleteCallRecordById(@Path("id") int id);

    // elevators
    @GET("elevators/")
    Observable<PaginatedResult<Elevator>> getElevatorList(@QueryMap Map<String, Object> map);

    @GET("elevators/realtime/")
    Observable<PaginationedResult<ElevatorRealtimeData>> getElevatorRealtimeDataList(@QueryMap Map<String, Object> map);

    @POST("elevators/")
    Observable<Elevator> addElevator(@Body Elevator elevator);

    @GET("elevators/{id}/")
    Observable<Elevator> getElevatorById(@Path("id") int id);

    @PUT("elevators/{id}/")
    Observable<Elevator> modifyElevatorById(@Path("id") int id, @Body Elevator elevator);

    @DELETE("elevators/{id}/")
    Observable<ResponseBody> deleteElevatorById(@Path("id") int id);

    // files
    //    @Multipart
    //    @POST("files/")
    //    Observable<ResponseBody> uploadFile(@Part List<MultipartBody.Part> files);
    @Multipart
    @POST("files/")
    Observable<File> uploadFile(@Part MultipartBody.Part file);

    // terminals
    @GET("terminals/")
    Observable<List<Terminal>> getTerminalList(@QueryMap Map<String, Object> map);
}