import React from "react"

export interface OperationStepCategory {
    id: number,
    name: string,
    shortName: string
}

export interface OperationStepStatus {
    id: number,
    status: string
}

export interface OperationCategory {
    id: number,
    name: string,
    shortName: string
}

export interface Operator {
    id: number,
    operationCategory: OperationCategory
}


export interface OperationStep extends Record<string, any> {
    id: number,
    category: string,
    startTime: string,
    stopTime: string,
    operatorName: string,
    status: string
}




// export interface OperationStep {
//     id: number,
//     category: OperationStepCategory
//     status: OperationStepStatus,
//     start: String,
//     stop: String,
//     duration: String,
//     operator: {
//         id: 0,
//         operationCategory: {
//             id: 0,
//             name: string,
//             shortName: string
//         }
//     }
// }

export interface OperationStatus {
    id: number,
    status: string
}

export interface OperationData extends Record<string, any> {
    id: 0,
    batchId: string,
    steps: OperationStep[],
    duration: number,
    startTime: string,
    stopTime: string,
    status: OperationStatus
}

export interface OperationDataTableProps {
    data: OperationData[],

}


export interface OperationStepTableProps {
    data: OperationStep[],
}

export type DataRowType = {
    [key: string]: undefined
}

export type HeaderKeyPair = {
    key: string,
    headerName: string
}