import { HardwareType } from "./hardware-type";

export interface Hardware {
    id: number;
    code: string;
    name: string;
    price: number;
    type: HardwareType;
    available: number;
}
