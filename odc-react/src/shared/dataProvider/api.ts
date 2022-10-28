import { url } from "inspector";
import { string } from "yup";
import { OperationData, OperationInformation, OperationStep } from "../types/types";

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

type AddOperationStepRequest = {
    timeStamp: Date,
    operatorName: string
}

export const postOperationStep = async (payload: AddOperationStepRequest, operationId: string | undefined) => {
    const url = `${API_URL_OPERATIONS}/${operationId}/steps`
    return await post(url, payload);
}

const post = async <T>(url: string, payload: T): Promise<any> => {
    return await fetch(url, {
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(payload)
    })

}



const get = async <T>(url: string): Promise<T> => {
    const data = await fetch(url);
    return await data.json();
}







