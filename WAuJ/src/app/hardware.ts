import { HardwareType } from "./hardware-type";

export interface Hardware {
    code: string;
    name: string;
    price: number;
    type: HardwareType;
    available: number;
}
