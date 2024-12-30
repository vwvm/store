from dataclasses import dataclass


@dataclass
class ResponseData:
    trace_id: str
    code: int
    msg: str