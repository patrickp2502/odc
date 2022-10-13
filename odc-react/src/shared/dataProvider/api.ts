import { OperationData, OperationInformation, OperationStep } from "../types/interfaces";

const BASE_URL: string = 'http://localhost:8080/api/v1'
const API_URL_OPERATIONS: string = BASE_URL + '/operations'
//const API_URL_STEPS: string = BASE_URL + '/steps'

export const getOperations = async (): Promise<OperationData[]> => {
    return await get<Promise<OperationData[]>>(API_URL_OPERATIONS);
};
export const getOperation = async (operationId: string | undefined): Promise<OperationData> => {
    return await get<Promise<OperationData>>(`${API_URL_OPERATIONS}/${operationId}`);
};

export const getOperationSteps = async (operationId: string | undefined) => {
    return await get<Promise<OperationStep[]>>(`${API_URL_OPERATIONS}/${operationId}/steps`);
};

export const getOperationInformation = async (): Promise<OperationInformation> => {
    return await get<Promise<OperationInformation>>(`${API_URL_OPERATIONS}/information`);
}



const get = async <T>(url: string): Promise<T> => {
    const data = await fetch(url);
    return await data.json();
}







