import { OperationData } from "../types/interfaces";

const BASE_URL: string = 'http://localhost:8080/api/v1'
const API_URL_ORDERS: string = BASE_URL + '/operations'
//const API_URL_STEPS: string = BASE_URL + '/steps'

export const getOperations = async (): Promise<OperationData[]> => {
    const data = await fetch(API_URL_ORDERS);
    return await data.json();
};



export const getOperationsSteps = async () => {
}
export const getOperationStep = async (operationId: number) => {
}
export const getOperation = async (operationId: number) => {
}





