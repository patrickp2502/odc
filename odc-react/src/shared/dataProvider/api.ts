import { OperationData } from "../types/interfaces";

const BASE_URL: string = 'http://localhost:8080/api/v1'
const API_URL_OPERATIONS: string = BASE_URL + '/operations'
//const API_URL_STEPS: string = BASE_URL + '/steps'

export const getOperations = async (): Promise<OperationData[]> => {
    return await get<Promise<OperationData[]>>(API_URL_OPERATIONS);
};
export const getOperation = async (operationId: string | undefined): Promise<OperationData> => {
    return await get<Promise<OperationData>>(`${API_URL_OPERATIONS}/${operationId}`)
}

export const getOperationsSteps = async () => {
}
export const getOperationStep = async (operationId: number) => {
}

const get = async <T>(url: string): Promise<T> => {
    const data = await fetch(url);
    return await data.json();
}







