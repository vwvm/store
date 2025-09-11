from fastapi import FastAPI, Request
import uvicorn
import datetime

app = FastAPI(title="Simple Logging API")


# 简单的日志函数
def log_request(endpoint: str, client_ip: str):
    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    print(f"[{timestamp}] Request to {endpoint} from {client_ip}")


# 根路径
@app.get("/")
async def root(request: Request):
    log_request("/", request.client.host)
    return {"status": "success", "message": "Hello from the server!"}


# 资源路径示例
@app.get("/resources")
async def get_resources(request: Request):
    log_request("/resources", request.client.host)
    return {"status": "success", "data": {"cpu": "normal", "memory": "normal"}}


# 操作路径示例
@app.post("/actions/{action_name}")
async def perform_action(action_name: str, request: Request):
    log_request(f"/actions/{action_name}", request.client.host)
    return {"status": "success", "action": action_name}


# 启动应用
if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=4567)