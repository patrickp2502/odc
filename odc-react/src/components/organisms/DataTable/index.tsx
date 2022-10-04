import { Table, TableBody, TableCell, TableHead, TableRow } from '@mui/material'
import React from 'react'


interface HeaderDataKeyMapTemplate {
    key: string,
    headerName: string
}

interface DataTableProps {
    headerCellNames: string[],
    dataObject?: Object[]
    headerKeyTemplate?: HeaderDataKeyMapTemplate[]
}

const rowConverter = (): Object[] | null => {
    //todo add converter for mapping header to object key to access only needed datarows
    return null
}

const DataTable: React.FC<DataTableProps> = (props) => {
    return (
        <Table>
            <TableHead>
                {props.headerCellNames.map(headerName => <TableCell>{headerName}</TableCell>)}
            </TableHead>
            <TableBody>
                {props.dataObject?.map(dataRow => {
                    return (
                        <TableCell>
                            {Object.keys(dataRow)}
                        </TableCell>)
                })
                }

            </TableBody>
        </Table>
    )
}

export default DataTable